package com.example.oauth.repository

import com.example.oauth.domain.Credential
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<Credential, String> {

    fun findOneByUsername(username: String): Optional<Credential>
}