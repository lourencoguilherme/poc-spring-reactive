package com.example.springbootkotlinreactiveexample.domain.user.repository.impl

import com.example.springbootkotlinreactiveexample.domain.user.repository.entity.UserEntity
import com.example.springbootkotlinreactiveexample.domain.user.repository.UserRepository
import com.example.springbootkotlinreactiveexample.util.find
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Repository
class UserRepositoryImpl(
    webClientBuilder: WebClient.Builder,
    @Value("\${users.api.url}") factoryUrl: String,
): UserRepository {

    private val log = LoggerFactory.getLogger(javaClass)

    val webClient = webClientBuilder
        .baseUrl("$factoryUrl")
        .build()

    override suspend fun findAllUsers(): List<UserEntity> {
        println("---> " + "just")
        val uri = "/users";
        val externalTodos = webClient.get()
            .uri(uri)
            .retrieve()
            .awaitBody<MutableList<UserEntity>>()

        return externalTodos.toList()
    }


    fun fallback(ex: Throwable): List<UserEntity> {
        println("---> " + ex.message)
        val userEntity = UserEntity(
            adderss = UserEntity.AdderssEntity(city = "", state = "", zipCode = ""),
            email = "",
            firstname = "",
            lastname = "",
            userId = "",
        )
        return listOf(userEntity)
    }
}