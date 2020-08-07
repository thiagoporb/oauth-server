package com.example.oauth.domain

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "authority")
data class Authority(@Id //@NotNull
                     //@Size(min = 0, max = 50)
                     val name: String? = null) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
}