package com.example.springbootkotlinreactiveexample.configuration

import com.example.springbootkotlinreactiveexample.SpringBootKotlinReactiveApplication
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [SpringBootKotlinReactiveApplication::class])
class MongoConfig(private val databaseProperties: DatabaseProperties) : AbstractReactiveMongoConfiguration() {

    override fun configureClientSettings(builder: MongoClientSettings.Builder) {
        builder.credential(MongoCredential.createScramSha256Credential(
            this.databaseProperties.username,
            this.databaseProperties.userLocation,
            this.databaseProperties.password.toCharArray()
        ))

        builder.applyToClusterSettings {
            it.hosts(listOf(ServerAddress(this.databaseProperties.host, this.databaseProperties.port)))
        }

    }

    override fun getDatabaseName(): String {
        return this.databaseProperties.database
    }

}