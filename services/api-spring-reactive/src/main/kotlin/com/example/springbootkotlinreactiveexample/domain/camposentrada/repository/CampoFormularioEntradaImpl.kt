package com.example.springbootkotlinreactiveexample.domain.camposentrada.repository

import com.example.springbootkotlinreactiveexample.domain.camposentrada.dao.CampoFormularioEntrada
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.*
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.util.*
import java.util.Map


@Repository
class CampoFormularioEntradaImpl(
    @Value("\${app.aws.dynamodb.tables.camposentrada}") private val tableName: String,
    dynamoDbEnhancedClient: DynamoDbEnhancedAsyncClient
) : CampoFormularioEntradaRepository {

    private val mappedTable: DynamoDbAsyncTable<CampoFormularioEntrada> =
        dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(CampoFormularioEntrada::class.java))
    val logger: Logger = LoggerFactory.getLogger(CampoFormularioEntradaImpl::class.java)


    override fun findAll(formularioId: UUID): Flux<CampoFormularioEntrada> {

        val expression = Expression.builder()
            .expressionValues(
                Map.of(
                    ":formularioId", AttributeValue.fromS("$formularioId")
                )
            )
            .expression("formularioId = :formularioId")
            .build()
        val scanEnhancedRequest = ScanEnhancedRequest.builder()
            .filterExpression(expression).consistentRead(true).build()

        val providerPage = mappedTable.scan(scanEnhancedRequest)
        return Flux.from(providerPage.items())

    }

    override fun findOne(campoFormularioEntradaId: UUID, formularioId: UUID): Flux<CampoFormularioEntrada> {

        logger.info("=> findOne :: id {} tableName {}", campoFormularioEntradaId, tableName)
        val key = Key.builder().partitionValue("$campoFormularioEntradaId").partitionValue("$formularioId") .build()


        val whereClause = "formularioId = :formularioId and campoFormularioEntradaId = :campoFormularioEntradaId"
        val expression = Expression.builder()
            /*.expressionNames(
                Map.of(
                    "#formularioId", "formularioId",
                    "#campoFormularioEntradaId", "campoFormularioEntradaId"
                )
            )*/
            .expressionValues(Map.of(
                ":formularioId", AttributeValue.fromS("$formularioId"),
                ":campoFormularioEntradaId", AttributeValue.fromS("$campoFormularioEntradaId")))
            .expression(whereClause)
            .build()
        val scanEnhancedRequest = ScanEnhancedRequest.builder().filterExpression(expression)
        val providerPage = mappedTable.scan(scanEnhancedRequest.consistentRead(true).build())
        return Flux.from(providerPage.items())
        /*return Mono.fromFuture(mappedTable.getItem(key))
            .doOnError { logger.error("Exception while retrieving ${CampoFormularioEntrada::class.java.simpleName} information - $it") }

         */

    }

    override fun create(entity: CampoFormularioEntrada): Mono<CampoFormularioEntrada> {

        return Mono.fromFuture(mappedTable.putItem(entity))
            .map { entity }
            .doOnError { logger.error("Exception while saving ${CampoFormularioEntrada::class.java.simpleName} information - $it") }

    }

    private fun buildScanRequest(
        identifier: String,
        limit: Int,
        terms: String,
        filterExpression: Expression
    ): ScanEnhancedRequest? {
        val scanEnhancedRequestBuilder = ScanEnhancedRequest.builder()
            .limit(limit)
        if (!identifier.isEmpty()) {
            scanEnhancedRequestBuilder.exclusiveStartKey(Map.of("id", AttributeValue.fromS(identifier)))
        }
        if (!terms.isEmpty()) {
            scanEnhancedRequestBuilder.filterExpression(filterExpression)
        }
        return scanEnhancedRequestBuilder.build()
    }
}