package com.example.springbootkotlinreactiveexample.domain.user.service

import com.example.springbootkotlinreactiveexample.domain.user.model.User
import com.example.springbootkotlinreactiveexample.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Override
    suspend fun findAllUsers(): Flux<User> {
        return userRepository.findAllUsers()
    }
}