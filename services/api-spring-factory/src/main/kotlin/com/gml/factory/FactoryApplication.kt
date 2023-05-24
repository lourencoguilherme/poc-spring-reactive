package com.gml.factory

import io.micrometer.observation.ObservationRegistry
import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import kotlin.time.Duration.Companion.seconds

@SpringBootApplication
class TracingApplication

fun main(args: Array<String>) {
    runApplication<TracingApplication>(*args)
}

@RestController
class Controller(
    val observationRegistry: ObservationRegistry,
    webClientBuilder: WebClient.Builder,
) {

    val webClient = webClientBuilder
        .baseUrl("http://jsonplaceholder.typicode.com")
        .build()

    val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/test-factory/{id}")
    suspend fun test(@PathVariable id: Long): String {
        val currentObservation = observationRegistry.currentObservation
        observeCtx {
            currentObservation?.highCardinalityKeyValue("test_key", "test sample value")
            log.info("test log with tracing info")
        }

        observationRegistry.runObserved("delay") {
            delay(1.seconds)
        }

        // make web client call and return response
        val externalTodos = webClient.get()
            .uri("/todos/$id")
            .retrieve()
            .bodyToMono(String::class.java)
            .awaitSingle()

        return "$externalTodos"
    }
}
