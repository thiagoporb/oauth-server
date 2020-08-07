package com.example.oauth.domain.util

import org.hibernate.dialect.H2Dialect
import java.sql.Types

/**
 *
 * FixedH2Dialect class.
 */
class FixedH2Dialect : H2Dialect() {
    /**
     *
     * Constructor for FixedH2Dialect.
     */
    init {
        registerColumnType(Types.FLOAT, "real")
    }
}