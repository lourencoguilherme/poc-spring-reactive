package com.gml.reactive

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactivefeign.spring.config.EnableReactiveFeignClients

@EnableReactiveFeignClients
@SpringBootApplication
class TracingApplication

fun main(args: Array<String>) {
    runApplication<TracingApplication>(*args)
}

@RestController
class Controller(
    val webClientFeign: CustomerServiceClient,
    webClientBuilder: WebClient.Builder,
) {

    val webClient = webClientBuilder
        .baseUrl("http://localhost:8081/")
        .build()

    @GetMapping("/test-tracing/{id}")
    suspend fun test(@PathVariable id: Long): String {
        //val externalTodos = webClient.getCustomer(id).awaitSingle()
        val externalTodos = webClient.get()
            .uri("/test-factory/$id")
            .retrieve()
            .bodyToMono(String::class.java)
            .awaitSingle()
        return "$externalTodos"
    }
}
