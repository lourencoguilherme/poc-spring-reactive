package com.example.springbootkotlinreactiveexample.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI

@Configuration
class DynamoConfiguration(
    private val environment: Environment,
    @Value("\${app.aws.dynamodb.endpoint}") private val dynamoUrl: String,
    @Value("\${app.aws.region}") private val region: String
) {

    val logger: Logger = LoggerFactory.getLogger(DynamoConfiguration::class.java)

    @Bean
    fun getDynamoDbEnhancedClient(): DynamoDbEnhancedAsyncClient {

        return DynamoDbEnhancedAsyncClient.builder()
            .dynamoDbClient(getDynamoDbClient())
            .build()
    }

    @Bean
    fun getDynamoDbClient(): DynamoDbAsyncClient {

        logger.info("dynamoUrl {}, region {}", dynamoUrl, region)

        return DynamoDbAsyncClient.builder()
            .endpointOverride(URI.create(dynamoUrl))
            .region(Region.of(region))
            .credentialsProvider(DefaultCredentialsProvider.builder().build())
            .build()
    }
}