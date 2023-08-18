package com.example.springbootkotlinreactiveexample.config.db

import com.example.springbootkotlinreactiveexample.domain.customer.domain.Customer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient

import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.regions.Region

import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient

import java.net.URI


@Configuration
class DynamoDbConfig{
    @Bean
    fun dynamoDbClient(
        @Value("\${app.aws.region}") region: String?,
        @Value("\${app.aws.dynamodb.endpoint}") dynamoDBEndpoint: String?
    ): DynamoDbAsyncClient? {
        return DynamoDbAsyncClient.builder()
            .endpointOverride(URI.create(dynamoDBEndpoint))
            .region(Region.of(region))
            .credentialsProvider(DefaultCredentialsProvider.builder().build())
            .build()
    }

    @Bean
    fun getDynamoDbEnhancedAsyncClient(dynamoDbAsyncClient: DynamoDbAsyncClient): DynamoDbEnhancedAsyncClient {
        return DynamoDbEnhancedAsyncClient.builder()
            .dynamoDbClient(dynamoDbAsyncClient)
            .build()
    }

    @Bean
    fun getDynamoDbAsyncCustomer(asyncClient: DynamoDbEnhancedAsyncClient): DynamoDbAsyncTable<Customer> {
        return asyncClient.table(Customer::class.java.simpleName, TableSchema.fromBean(Customer::class.java))
    }
}