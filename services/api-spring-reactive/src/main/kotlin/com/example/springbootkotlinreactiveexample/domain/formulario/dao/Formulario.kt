package com.example.springbootkotlinreactiveexample.domain.formulario.dao

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.util.UUID

@DynamoDbBean
data class Formulario(

    @get:DynamoDbPartitionKey var formularioId: UUID = UUID.randomUUID(),
    var url: String? = null,
    var nome: String? = null,
    var publico: Boolean? = null,
    var titulo: String? = null,
    var logoId: String? = null,
    var planoFundoId: String? = null,
)