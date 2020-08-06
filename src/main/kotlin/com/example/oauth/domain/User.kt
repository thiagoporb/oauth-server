package com.example.oauth.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.core.GrantedAuthority


@Document(collection = "user")
data class User(@Id
                @Field("username")
                val username: String,
                @Field("name")
                val name: String,
                @Field("password")
                @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                val password: String,
                @Field("authorities")
                val authorities: MutableCollection<out GrantedAuthority>)