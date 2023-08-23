package com.example.springbootkotlinreactiveexample.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
@Component
class DatabaseProperties(
    @Value("\${app.mongodb.host}") val host: String,
    @Value("\${app.mongodb.port}") val port: Int,
    @Value("\${app.mongodb.database}") val database: String,
    @Value("\${app.mongodb.username}") val username: String,
    @Value("\${app.mongodb.password}") val password: String,
    @Value("\${app.mongodb.userLocation}") val userLocation: String,
)