package com.example.springbootkotlinreactiveexample.domain.processos.dao

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.util.UUID

@DynamoDbBean
data class Processo(

    @get:DynamoDbPartitionKey var processoId: UUID = UUID.randomUUID(),
    var name: String? = null
)