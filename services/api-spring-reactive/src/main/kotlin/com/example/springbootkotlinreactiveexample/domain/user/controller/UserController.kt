package com.example.springbootkotlinreactiveexample.domain.user.controller

import com.example.springbootkotlinreactiveexample.domain.user.model.User
import com.example.springbootkotlinreactiveexample.domain.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val service: UserService
) {
    @GetMapping("/users")
    suspend fun test(): List<User> {
        return service.findAllUsers()
    }
}