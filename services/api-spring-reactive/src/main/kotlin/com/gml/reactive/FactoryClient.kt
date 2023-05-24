package com.gml.reactive

import feign.RetryableException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Indexed
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.ReactiveOptions
import reactivefeign.client.ReactiveHttpResponse
import reactivefeign.client.log.DefaultReactiveLogger
import reactivefeign.client.log.ReactiveLoggerListener
import reactivefeign.client.statushandler.ReactiveStatusHandler
import reactivefeign.client.statushandler.ReactiveStatusHandlers
import reactivefeign.retry.BasicReactiveRetryPolicy
import reactivefeign.retry.ReactiveRetryPolicy
import reactivefeign.spring.config.ReactiveFeignClient
import reactivefeign.webclient.WebReactiveOptions
import reactor.core.publisher.Mono
import java.time.Clock
import java.time.Instant
import java.util.*
import java.util.function.BiFunction

@ReactiveFeignClient(
    name = "customer-service",
    url = "\${customer-service.urls.base-url}",
    configuration = [CustomerClientConfig::class],
)
interface CustomerServiceClient {

    @GetMapping(value = ["\${customer-service.urls.get-customer-url}"])
    fun getCustomer(@PathVariable("id") id: Long?): Mono<String>
}

@Configuration
@ConfigurationProperties
@Indexed
class CustomerClientConfig {
    @Value("\${customer-service.http-client.read-timeout}")
    private val readTimeout = 0

    @Value("\${customer-service.http-client.write-timeout}")
    private val writeTimeout = 0

    @Value("\${customer-service.http-client.connect-timeout}")
    private val connectTimeout = 0

    @Value("\${customer-service.http-client.response-timeout}")
    private val responseTimeout = 0

    @Value("\${customer-service.retry.max-retry}")
    private val maxRetry = 0

    @Value("\${customer-service.retry.retry-interval}")
    private val retryInterval = 0

    @Bean
    fun loggerListener(): ReactiveLoggerListener<*> {
        return DefaultReactiveLogger(Clock.systemUTC(), LoggerFactory.getLogger(this::class.java.getName()))
    }

    @Bean
    fun reactiveRetryPolicy(): ReactiveRetryPolicy {
        return BasicReactiveRetryPolicy.retryWithBackoff(maxRetry, retryInterval.toLong())
    }

    @Bean
    fun reactiveStatusHandler(): ReactiveStatusHandler {
        return ReactiveStatusHandlers.throwOnStatus(
            { status: Int -> status == 500 },
            errorFunction(),
        )
    }

    private fun errorFunction(): BiFunction<String, ReactiveHttpResponse<*>, Throwable> {
        return BiFunction<String, ReactiveHttpResponse<*>, Throwable> { methodKey, response ->
            RetryableException(
                response.status(),
                "",
                null,
                Date.from(Instant.EPOCH),
                null,
            )
        }
    }

    @Bean
    fun reactiveOptions(): ReactiveOptions {
        return WebReactiveOptions.Builder()
            .setReadTimeoutMillis(readTimeout.toLong())
            .setWriteTimeoutMillis(writeTimeout.toLong())
            .setResponseTimeoutMillis(responseTimeout.toLong())
            .setConnectTimeoutMillis(connectTimeout.toLong())
            .build()
    }
}
