package com.example.springbootkotlinreactiveexample.repository

import com.example.springbootkotlinreactiveexample.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
data class DataCreator(
    private val repository: TodoRepository
) : ApplicationRunner {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(args: ApplicationArguments?): Unit = runBlocking {
        // Test coroutine functionality
        launch(Dispatchers.Default) {
            //repository.save(Todo(0, "{}"))
            logger.info("Added initial news to database")
        }
    }
}
