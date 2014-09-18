package pl.warsjawa.microservice.config

import com.mongodb.Mongo
import com.mongodb.WriteConcern
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoFactoryBean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.core.WriteResultChecking
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@TypeChecked
@Configuration
@EnableMongoRepositories('pl.warsjawa.marketing')
class DbConfiguration {

    @Bean
    MongoFactoryBean mongo(@Value('${mongodb.host:localhost}') String host,
                           @Value('${mongodb.port:27017}') int port) {
        return new MongoFactoryBean(host: host, port: port, writeConcern: WriteConcern.FSYNCED)
    }

    @Bean
    MongoDbFactory mongoDbFactory(Mongo mongo, @Value('${mongodb.dbname:client}') String dbName) {
        return new SimpleMongoDbFactory(mongo, dbName)
    }

    @Bean
    MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory)
        mongoTemplate.writeResultChecking = WriteResultChecking.EXCEPTION
        return mongoTemplate
    }

}