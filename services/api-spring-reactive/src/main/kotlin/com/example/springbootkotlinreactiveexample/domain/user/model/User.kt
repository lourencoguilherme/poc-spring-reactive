package com.example.springbootkotlinreactiveexample.domain.user.model
data class User(
    val adderss: Adderss,
    val email: String,
    val firstname: String,
    val lastname: String,
    val userId: String
) {
    data class Adderss(
        val city: String,
        val state: String,
        val zipCode: String
    )
}
