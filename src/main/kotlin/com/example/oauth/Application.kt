package com.example.oauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories(basePackages = ["com.example.oauth.repository"])
//@EnableEurekaClient
@SpringBootApplication
class Application

fun main(args: Array<String>) {

    runApplication<Application>(*args)
}
