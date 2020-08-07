package com.example.oauth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(private val userDetailsService: UserDetailsService,
                                private val dataSource: DataSource) : AuthorizationServerConfigurerAdapter() {

    @Value("\${security.oauth2.client.client-id}")
    private lateinit var clientId: String

    @Value("\${security.oauth2.client.authorized-grant-types}")
    private lateinit var authorizedGrantTypes: Array<String>

    @Value("\${security.oauth2.client.resource-ids}")
    private lateinit var resourceIds: String

    @Value("\${security.oauth2.client.scope}")
    private lateinit var scopes: Array<String>

    @Value("\${security.oauth2.client.client-secret}")
    private lateinit var secret: String

    @Value("\${security.oauth2.client.access-token-validity-seconds}")
    private var accessTokenValiditySeconds: Int = 3600

    @Autowired
    @Qualifier("authenticationManagerBean")
    private lateinit var authenticationManager: AuthenticationManager

    @Bean
    fun tokenStore(): JdbcTokenStore? {
        return JdbcTokenStore(dataSource)
    }

    @Bean
    @Primary
    fun tokenServices(): DefaultTokenServices? {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore())
        defaultTokenServices.setSupportRefreshToken(true)
        defaultTokenServices.setAuthenticationManager(authenticationManager)
        return defaultTokenServices
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .authenticationManager(this.authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
    }

    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()")
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder())
//                .withClient(clientId)
//                .secret(secret)
//                .authorizedGrantTypes(*authorizedGrantTypes)
//                .authorities(*Authorities.names())
//                .resourceIds(resourceIds)
//                .scopes(*scopes)
//                .accessTokenValiditySeconds(accessTokenValiditySeconds).and().build();
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}