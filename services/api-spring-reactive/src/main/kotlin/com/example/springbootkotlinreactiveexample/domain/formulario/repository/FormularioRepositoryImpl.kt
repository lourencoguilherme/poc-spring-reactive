package com.example.springbootkotlinreactiveexample.domain.formulario.repository

import com.example.springbootkotlinreactiveexample.domain.formulario.dao.Formulario
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
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest
import java.util.*

@Repository
class FormularioRepositoryImpl(
    @Value("\${app.aws.dynamodb.tables.formularios}") private val tableName: String,
    dynamoDbEnhancedClient: DynamoDbEnhancedAsyncClient
) : FormularioRepository {

    private val mappedTable: DynamoDbAsyncTable<Formulario> =
        dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(Formulario::class.java))
    val logger: Logger = LoggerFactory.getLogger(FormularioRepositoryImpl::class.java)


    override fun findAll(): Flux<Formulario> {

        logger.info("=> findAllStudents :: tableName {}", tableName)

        return Flux.from(mappedTable.scan(ScanEnhancedRequest.builder().consistentRead(true).build()).items())

    }

    override fun findOne(formularioId: UUID): Mono<Formulario> {

        logger.info("=> findOne :: id {} tableName {}", formularioId, tableName)

        val key = Key.builder().partitionValue("$formularioId").build()
        return Mono.fromFuture(mappedTable.getItem(key))
            .doOnError { logger.error("Exception while retrieving ${Formulario::class.java.simpleName} information - $it") }

    }

    override fun create(formulario: Formulario): Mono<Formulario> {

        return Mono.fromFuture(mappedTable.putItem(formulario))
            .map { formulario }
            .doOnError { logger.error("Exception while saving ${Formulario::class.java.simpleName} information - $it") }

    }
}