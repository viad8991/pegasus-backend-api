package org.pegasus.backendapi.utils

import org.dbunit.dataset.datatype.AbstractDataType
import org.dbunit.dataset.datatype.DataType
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory
import org.postgresql.util.PGobject
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

class DBRiderPostgresqlDataTypeFactory : PostgresqlDataTypeFactory() {

    override fun createDataType(sqlType: Int, sqlTypeName: String?): DataType {
        return when (sqlTypeName) {
            "jsonb" -> JsonbDataType()
            else -> super.createDataType(sqlType, sqlTypeName)
        }
    }

    class JsonbDataType : AbstractDataType("jsonb", Types.OTHER, String::class.java, false) {
        override fun typeCast(any: Any): String = any.toString()

        override fun getSqlValue(column: Int, resultSet: ResultSet): String = resultSet.getString(column)

        override fun setSqlValue(value: Any?, column: Int, statement: PreparedStatement) {
            val pGobject = PGobject()
            pGobject.type = "jsonb"
            pGobject.value = value?.toString()

            statement.setObject(column, pGobject)
        }
    }

}
