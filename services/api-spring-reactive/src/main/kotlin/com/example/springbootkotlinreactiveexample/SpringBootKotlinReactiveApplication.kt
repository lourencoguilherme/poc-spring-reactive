package com.example.springbootkotlinreactiveexample

import com.example.springbootkotlinreactiveexample.dto.ErrorDTO
import com.example.springbootkotlinreactiveexample.dto.TodoDTO
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@RegisterReflectionForBinding(
    classes = [
        TodoDTO::class,
        ErrorDTO::class,
    ],
)
@SpringBootApplication
class SpringBootKotlinReactiveApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinReactiveApplication>(*args)
}
