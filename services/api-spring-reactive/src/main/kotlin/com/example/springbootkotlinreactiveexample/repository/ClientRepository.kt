package com.example.springbootkotlinreactiveexample.repository

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class ClientRepository(
    webClientBuilder: WebClient.Builder,
) {
    val webClient = webClientBuilder
        .baseUrl("http://localhost:8081/")
        .build()

    suspend fun test(id: Long): String {
        val externalTodos = webClient.get()
            .uri("/test-factory/$id")
            .retrieve()
            .bodyToMono(String::class.java)
            .awaitSingle()
        return "$externalTodos"
    }
}
