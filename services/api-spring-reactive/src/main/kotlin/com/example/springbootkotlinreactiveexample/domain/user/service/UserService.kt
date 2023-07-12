package com.example.springbootkotlinreactiveexample.domain.user.service

import com.example.springbootkotlinreactiveexample.domain.user.model.User
import com.example.springbootkotlinreactiveexample.domain.user.repository.entity.UserEntity
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

const val BACKEND_A = "backendA"

@Service
class UserService(
    webClientBuilder: WebClient.Builder,
    @Value("\${users.api.url}") factoryUrl: String,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    val webClient = webClientBuilder
        .baseUrl("$factoryUrl")
        .build()

    @Override
    @CircuitBreaker(name = BACKEND_A)
    @Bulkhead(name = BACKEND_A)
    @Retry(name = BACKEND_A)
    suspend fun findAllUsers(): List<User> {
        println("---> " + "just")
        val uri = "/users";
        //val externalTodos = webClient.find<UserEntity>(uriParam = uri)

        val externalTodos = webClient.get()
            .uri(uri)
            .retrieve()
            .awaitBody<MutableList<UserEntity>>()

        return externalTodos.map { it.toUser() }
        throw Exception("teste")
    }


    fun fallback(ex: Throwable): List<User> {
        println("---> " + ex.message)
        val userEntity = User(
            adderss = User.Adderss(city = "", state = "", zipCode = ""),
            email  = "",
            firstname = "",
            lastname = "",
            userId = "",
        )
        return listOf(userEntity)
    }
}