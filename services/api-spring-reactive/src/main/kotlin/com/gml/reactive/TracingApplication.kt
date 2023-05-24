package com.gml.reactive

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactivefeign.spring.config.EnableReactiveFeignClients

@EnableReactiveFeignClients
@SpringBootApplication
class TracingApplication

fun main(args: Array<String>) {
    runApplication<TracingApplication>(*args)
}

@RestController
class Controller(
    val webClient: CustomerServiceClient,
) {

    @GetMapping("/test-tracing/{id}")
    suspend fun test(@PathVariable id: Long): String {
        val externalTodos = webClient.getCustomer(id).awaitSingle()
        return "$externalTodos"
    }
}
