package com.example.springbootkotlinreactiveexample.controller

import com.example.springbootkotlinreactiveexample.data.Todo
import com.example.springbootkotlinreactiveexample.data.toDTO
import com.example.springbootkotlinreactiveexample.dto.TodoDTO
import com.example.springbootkotlinreactiveexample.repository.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest
@Import(RouteConfig::class, Handler::class)
class MockIntegrationTest(
    @Autowired val client: WebTestClient
) {

    @MockkBean
    private lateinit var repository: TodoRepository

    @Test
    fun `GET news by id test`() {
        val todo = Todo(123,"Test", "Some hot news", "test.com")

        coEvery {
            repository.findById(any())
        } coAnswers {
            todo
        }

        client
            .get()
            .uri("/api/v1/news/123")
            .exchange()
            .expectStatus().isOk
            .expectBody<Todo>()
            .isEqualTo(todo)
    }

    @Test
    fun `GET all news test`() {
        val todo = Todo(123,"Test", "Some hot news", "test.com")

        coEvery {
            repository.findAll()
        } coAnswers {
            flowOf(todo)
        }

        client
            .get()
            .uri("/api/v1/news")
            .exchange()
            .expectStatus().isOk
            .expectBody<Array<TodoDTO>>()
            .isEqualTo(arrayOf(todo.toDTO()))
    }

    @Test
    fun `POST news test`() {
        val todo = Todo(123,"Test", "Some hot news", "test.com")

        coEvery {
            repository.findById(any())
        } coAnswers {
            todo
        }

        client
            .get()
            .uri("/api/v1/news/123")
            .exchange()
            .expectStatus().isOk
            .expectBody<TodoDTO>()
            .isEqualTo(todo.toDTO())
    }


}