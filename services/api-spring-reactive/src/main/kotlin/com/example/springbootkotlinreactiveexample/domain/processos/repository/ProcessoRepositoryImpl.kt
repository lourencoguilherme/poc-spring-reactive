package com.example.springbootkotlinreactiveexample.domain.processos.repository

import com.example.springbootkotlinreactiveexample.domain.processos.dao.Processo
import com.example.springbootkotlinreactiveexample.domain.formulario.repository.FormularioRepositoryImpl
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
class ProcessoRepositoryImpl(
    @Value("\${app.aws.dynamodb.tables.processos}") private val tableName: String,
    private val dynamoDbEnhancedClient: DynamoDbEnhancedAsyncClient
) : ProcessoRepository {

    private val mappedTable: DynamoDbAsyncTable<Processo> =
        dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(Processo::class.java))
    val logger: Logger = LoggerFactory.getLogger(FormularioRepositoryImpl::class.java)


    override fun findAllProcessos(): Flux<Processo> {

        logger.info("=> findAllProcessos :: tableName {}", tableName)
        return Flux.from(mappedTable.scan(ScanEnhancedRequest.builder().consistentRead(true).build()).items())

    }

    override fun getProcessos(processoId: UUID): Mono<Processo> {

        logger.info("=> getProcesso :: id {} tableName {}", processoId, tableName)

        val key = Key.builder().partitionValue("$processoId").build()
        return Mono.fromFuture(mappedTable.getItem(key))
            .doOnError { logger.error("Exception while retrieving Processo information - $it") }

    }

    override fun saveProcesso(processo: Processo): Mono<Processo> {

        return Mono.fromFuture(mappedTable.putItem(processo))
            .map { processo }
            .doOnError { logger.error("Exception while saving Processo information - $it") }

    }
}