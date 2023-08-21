package com.example.springbootkotlinreactiveexample.domain.student.dao

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
data class Student(

    @get:DynamoDbPartitionKey var studentId: Int = 0,
    var firstName: String? = null,
    var lastName: String? = null
)