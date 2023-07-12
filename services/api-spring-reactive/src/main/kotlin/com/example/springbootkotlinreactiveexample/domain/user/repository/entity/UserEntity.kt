package com.example.springbootkotlinreactiveexample.domain.user.repository.entity

import com.example.springbootkotlinreactiveexample.domain.user.model.User

data class UserEntity(
    val adderss: AdderssEntity,
    val email: String,
    val firstname: String,
    val lastname: String,
    val userId: String
) {
    data class AdderssEntity(
        val city: String,
        val state: String,
        val zipCode: String
    ) {
        fun toAddress() : User.Adderss {
            return User.Adderss(city = this.city, state = this.state, zipCode = this.zipCode)
        }
    }
    fun toUser() : User {
        return User(
            adderss = this.adderss.toAddress(),
            email = this.email,
            firstname = this.firstname,
            lastname = this.lastname,
            userId = this.userId,
        )
    }
}
