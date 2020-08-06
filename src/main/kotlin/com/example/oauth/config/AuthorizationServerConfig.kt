package com.example.oauth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
//@EnableAuthorizationServer
class AuthorizationServerConfiguration {

    @Bean
    fun authenticationManager(userDetailsService: ReactiveUserDetailsService, passwordEncoder: PasswordEncoder): ReactiveAuthenticationManager {
        val manager = UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService)
        manager.setPasswordEncoder(passwordEncoder)
        return manager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity, authenticationManager: ReactiveAuthenticationManager): SecurityWebFilterChain {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/oauth/token").permitAll()
                //.pathMatchers("/role").hasRole("ADMIN")
                //.pathMatchers("/test").access(new HasScope ("server")) //custom implementation
                .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                //.oauth2ResourceServer()
                //.opaqueToken()
                //.jwt()
                .authenticationManager(authenticationManager)
                .build();
    }


//    @Bean
//    fun authenticationManager(contextSource: BaseLdapPathContextSource?): ReactiveAuthenticationManager {
//        ReactiveAuthorizationManager
//        val ba = BindAuthenticator(contextSource)
//        ba.setUserDnPatterns(arrayOf("cn={0},ou=people"))
//        val lap = LdapAuthenticationProvider(ba)
//        val am: AuthenticationManager = ProviderManager(Arrays.asList(lap))
//        return ReactiveAuthenticationManagerAdapter(am)
//    }

}