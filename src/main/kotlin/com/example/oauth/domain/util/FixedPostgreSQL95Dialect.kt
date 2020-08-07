package com.example.oauth.domain.util

import org.hibernate.dialect.PostgreSQL95Dialect
import org.hibernate.type.descriptor.sql.BinaryTypeDescriptor
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor
import java.sql.Types

/**
 *
 * FixedPostgreSQL95Dialect class.
 */
class FixedPostgreSQL95Dialect : PostgreSQL95Dialect() {

    /** {@inheritDoc}  */
    override fun remapSqlTypeDescriptor(sqlTypeDescriptor: SqlTypeDescriptor): SqlTypeDescriptor {
        return if (sqlTypeDescriptor.sqlType == Types.BLOB) {
            BinaryTypeDescriptor.INSTANCE
        } else super.remapSqlTypeDescriptor(sqlTypeDescriptor)
    }

    /**
     *
     * Constructor for FixedPostgreSQL95Dialect.
     */
    init {
        registerColumnType(Types.BLOB, "bytea")
    }
}
