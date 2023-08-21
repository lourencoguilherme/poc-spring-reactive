package com.example.springbootkotlinreactiveexample.domain.user.repository

import com.example.springbootkotlinreactiveexample.domain.user.model.User
import reactor.core.publisher.Flux

interface UserRepository {
    suspend fun findAllUsers(): Flux<User>
}