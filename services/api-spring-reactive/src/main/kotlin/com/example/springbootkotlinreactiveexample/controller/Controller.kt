package com.example.springbootkotlinreactiveexample.controller

import com.example.springbootkotlinreactiveexample.data.toDTO
import com.example.springbootkotlinreactiveexample.dto.ErrorDTO
import com.example.springbootkotlinreactiveexample.dto.TodoDTO
import com.example.springbootkotlinreactiveexample.dto.toData
import com.example.springbootkotlinreactiveexample.repository.TodoRepository
import com.google.gson.Gson
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
class Controller(
    webClientBuilder: WebClient.Builder,
    private val repository: TodoRepository,
    @Value("\${factory.api.url}") factoryUrl: String,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    val webClient = webClientBuilder
        .baseUrl("$factoryUrl")
        .build()

    @GetMapping("/test-tracing/{id}")
    suspend fun test(@PathVariable id: Long): String {
        repository.findById(id)?.let {
            return it.body
        }

        val externalTodos = webClient.get()
            .uri("/test-factory/$id")
            .retrieve()
            .bodyToMono(String::class.java)
            .awaitSingle()

        externalTodos?.runCatching {
            val newsToSave = TodoDTO(id, externalTodos).copy(id = 0).toData()
            repository.save(newsToSave).toDTO()
            return newsToSave.body
        }?.getOrElse {
            log.error("Error saving news", it)
            return Gson().toJson(ErrorDTO("Error while saving news"))
        }

        return Gson().toJson(ErrorDTO(msg = "Invalid news id in path", fields = mapOf("id" to "$id")))
    }
}
