package com.example.springbootkotlinreactiveexample.domain.user.repository

import com.example.springbootkotlinreactiveexample.domain.user.repository.entity.UserEntity
interface UserRepository {
    suspend fun findAllUsers(): List<UserEntity>
}