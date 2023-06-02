package com.example.springbootkotlinreactiveexample.repository

import com.example.springbootkotlinreactiveexample.data.Todo
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TodoRepository : CoroutineCrudRepository<Todo, Long> {
    override fun findAll(): Flow<Todo>
    override suspend fun findById(id: Long): Todo?
    override suspend fun existsById(id: Long): Boolean
    override suspend fun <S : Todo> save(entity: S): Todo
    override suspend fun deleteById(id: Long)
}
