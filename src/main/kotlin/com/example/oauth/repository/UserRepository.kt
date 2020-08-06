package com.example.oauth.repository

import com.example.oauth.domain.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {

    fun findOneByUsername(username: String): Mono<User>
}