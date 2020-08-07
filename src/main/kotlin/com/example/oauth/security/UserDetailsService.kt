package com.example.oauth.security

import com.example.oauth.domain.Credential
import com.example.oauth.repository.CredentialRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


@Service
@Transactional
class UserDetailsService(private val userRepository: CredentialRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)
                .map {
                    User.withUsername(it.username).password(it.password).authorities(getGrantedAuthorities(it)).build()
                }.orElseThrow { UsernameNotFoundException("User $username Not found") }
    }

    private fun getGrantedAuthorities(user: Credential): Collection<GrantedAuthority> {
        val grantedAuthorities: MutableCollection<GrantedAuthority> = ArrayList()
        for (authority in user.authorities!!) {
            val grantedAuthority: GrantedAuthority = SimpleGrantedAuthority(authority.name)
            grantedAuthorities.add(grantedAuthority)
        }
        return grantedAuthorities
    }
}