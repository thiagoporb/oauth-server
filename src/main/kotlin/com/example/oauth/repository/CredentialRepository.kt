package com.example.oauth.repository

import com.example.oauth.domain.Credential
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CredentialRepository : JpaRepository<Credential, String> {

    fun findByUsername(username: String): Optional<Credential>
}