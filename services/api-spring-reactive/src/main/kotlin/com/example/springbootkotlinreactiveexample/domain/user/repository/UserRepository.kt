package com.example.springbootkotlinreactiveexample.domain.user.repository

import com.example.springbootkotlinreactiveexample.domain.user.model.User
interface UserRepository {
    suspend fun findAllUsers(): List<User>
}