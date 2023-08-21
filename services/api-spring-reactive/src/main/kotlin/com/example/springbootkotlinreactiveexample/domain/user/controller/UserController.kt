package com.example.springbootkotlinreactiveexample.domain.user.controller

import com.example.springbootkotlinreactiveexample.domain.user.model.User
import com.example.springbootkotlinreactiveexample.domain.user.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2
import java.time.Duration
import java.time.LocalDateTime

@RestController
class UserController(
    private val service: UserService
) {
    private var count = 0;
    @GetMapping("/users")
    suspend fun test(): Flux<User> {
        println("---Start get Playlists by WEBFLUX--- ${LocalDateTime.now()}")
        val valor = count++
        val resonse = service.findAllUsers()
        println("=> users :: count {$valor}")
        return resonse
    }
}