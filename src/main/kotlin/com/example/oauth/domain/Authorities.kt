package com.example.oauth.domain

enum class Authorities {

    ROLE_USER, ROLE_ADMIN;

    companion object {
        fun names(): Array<String?> {
            val names = arrayOfNulls<String>(values().size)
            for (index in values().indices) {
                names[index] = values()[index].name
            }
            return names
        }
    }
}