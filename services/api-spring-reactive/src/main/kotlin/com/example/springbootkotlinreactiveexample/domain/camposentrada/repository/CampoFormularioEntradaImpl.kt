package com.example.springbootkotlinreactiveexample.domain.camposentrada.repository

import com.example.springbootkotlinreactiveexample.domain.camposentrada.dao.CampoFormularioEntrada
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.keyEqualTo
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest
import java.util.*

@Repository
class CampoFormularioEntradaImpl(
    @Value("\${app.aws.dynamodb.tables.camposentrada}") private val tableName: String,
    dynamoDbEnhancedClient: DynamoDbEnhancedAsyncClient
) : CampoFormularioEntradaRepository {

    private val mappedTable: DynamoDbAsyncTable<CampoFormularioEntrada> =
        dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(CampoFormularioEntrada::class.java))
    val logger: Logger = LoggerFactory.getLogger(CampoFormularioEntradaImpl::class.java)


    override fun findAll(): Flux<CampoFormularioEntrada> {

        logger.info("=> findAllStudents :: tableName {}", tableName)

        return Flux.from(mappedTable.scan(ScanEnhancedRequest.builder().consistentRead(true).build()).items())

    }

    override fun findOne(id: UUID, formularioId: UUID): Mono<CampoFormularioEntrada> {

        logger.info("=> findOne :: id {} tableName {}", id, tableName)
        val key = Key.builder().partitionValue("$id").partitionValue("$formularioId") .build()
        return Mono.fromFuture(mappedTable.getItem(key))
            .doOnError { logger.error("Exception while retrieving ${CampoFormularioEntrada::class.java.simpleName} information - $it") }

    }

    override fun create(entity: CampoFormularioEntrada): Mono<CampoFormularioEntrada> {

        return Mono.fromFuture(mappedTable.putItem(entity))
            .map { entity }
            .doOnError { logger.error("Exception while saving ${CampoFormularioEntrada::class.java.simpleName} information - $it") }

    }
}