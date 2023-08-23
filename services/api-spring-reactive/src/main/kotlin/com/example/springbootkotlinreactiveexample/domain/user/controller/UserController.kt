package com.example.springbootkotlinreactiveexample.domain.user.controller

import com.example.springbootkotlinreactiveexample.domain.user.model.User
import com.example.springbootkotlinreactiveexample.domain.user.service.UserService
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
    @GetMapping("/users")
    suspend fun test(): Flux<Tuple2<Long, User>> {
        println("---Start get Playlists by WEBFLUX--- ${LocalDateTime.now()}")
        val resonse = service.findAllUsers()
        val interval = Flux.interval(Duration.ofSeconds(10))
        return Flux.zip(interval, resonse)
    }
    @GetMapping("/block")
    suspend fun block(): String {
        Thread.sleep(100_000)
        return "teste"
    }
}