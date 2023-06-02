package com.example.springbootkotlinreactiveexample.data

import com.example.springbootkotlinreactiveexample.dto.TodoDTO
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("todos")
data class Todo(
    @Id
    val id: Long = 0,

    val body: String
)

fun Todo.toDTO(): TodoDTO {
    return TodoDTO(id, body)
}

