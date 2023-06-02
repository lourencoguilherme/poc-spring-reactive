package com.example.springbootkotlinreactiveexample.dto

import com.example.springbootkotlinreactiveexample.data.Todo
import kotlinx.serialization.Serializable

@Serializable
data class TodoDTO(
    val id: Long = 0,
    val body: String
)

fun TodoDTO.toData(): Todo {
    return Todo(id, body)
}
