package com.example.springbootkotlinreactiveexample.domain.customer.domain

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey
import java.time.Instant

@DynamoDbBean
data class Customer(
    @get:DynamoDbPartitionKey
    val customerId: String,
    val name: String,
    @get:DynamoDbSortKey
    private var createdTimeStamp: Long
) {
    constructor() : this("", "", Instant.now()
        .epochSecond
    )
}