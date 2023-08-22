package com.example.springbootkotlinreactiveexample.domain.camposentrada.dao

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.util.UUID

@DynamoDbBean
data class CampoFormularioEntrada(

    @get:DynamoDbPartitionKey var campoFormularioEntradaId: UUID = UUID.randomUUID(),
    var formularioId: UUID? = null,
    var titulo: String? = null,
    var descricaoExibicao: String? = null,
    var obrigatorio: Boolean = false,
    var ordem: Integer? = null,
    var tamanhoMinimo: Integer? = null,
    var tamanhoMaximo: Integer? = null,
    var mascara: String? = null,
    var editavelOutrasEtapas: Boolean? = false,
    var visivelResumo: Boolean? = true,
    var visivel: Boolean? = true,
) {
    fun defineFormularioId(formularioId: UUID) {
        this.formularioId = formularioId
    }
}