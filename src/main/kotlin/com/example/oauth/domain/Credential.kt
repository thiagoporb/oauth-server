package com.example.oauth.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "credential")
data class Credential(@Id
                      @Column(updatable = false, nullable = false)
                      val username: String? = null,
        //@Size(min = 0, max = 500)
                      val password: String? = null,
        //@Email
        //@Size(min = 0, max = 50)
                      val email: String? = null,
                      val activated: Boolean = false,
//@Size(min = 0, max = 100)
                      @Column(name = "activationkey")
                      val activationKey: String? = null,
//@Size(min = 0, max = 100)
                      @Column(name = "resetpasswordkey")
                      val resetPasswordKey: String? = null,
                      @ManyToMany
                      @JoinTable(name = "credential_authority", joinColumns = [JoinColumn(name = "username")], inverseJoinColumns = [JoinColumn(name = "authority")])
                      val authorities: MutableCollection<Authority>? = null) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
}