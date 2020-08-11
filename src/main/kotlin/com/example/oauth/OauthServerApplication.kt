package com.example.oauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories(basePackages = ["com.example.oauth.repository"])
//@EnableEurekaClient
@SpringBootApplication
class OauthServerApplication

fun main(args: Array<String>) {

    runApplication<OauthServerApplication>(*args)
}
