package com.example.oauth.security

import com.example.oauth.repository.UserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ReactiveUserDetailsService(private val userRepository: UserRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {
        return this.userRepository.findOneByUsername(username).map { user ->
            User(user.username, user.password, user.authorities)
        };
    }
}