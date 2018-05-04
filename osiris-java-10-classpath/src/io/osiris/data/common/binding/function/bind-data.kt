@file:JvmName("DataBindingHandler")

package io.osiris.data.common.binding.function

import io.osiris.data.common.annotation.Column
import io.osiris.data.common.annotation.Id
import io.osiris.data.common.dto.DTO
import java.lang.reflect.Field
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Timestamp
import java.util.Arrays
import kotlin.collections.ArrayList

fun fetchColumns(dtoClass: Class<*>): List<String> {
    val columns = ArrayList<String>()
    
    if (dtoClass.superclass != null) {
        columns.addAll(fetchColumns(dtoClass.superclass))
    }
    
    dtoClass.declaredFields
            .asSequence()
            .mapNotNull { it.getAnnotation(Column::class.java) }
            .mapTo(columns) { it.value }
    
    return columns
}

fun fetchFields(dtoClass: Class<*>): List<Field> {
    val fieldList = ArrayList<Field>()
    
    if (dtoClass.superclass != null) {
        fieldList.addAll(fetchFields(dtoClass.superclass))
    }
    
    val modelFieldArray = dtoClass.declaredFields
    fieldList.addAll(Arrays.asList(*modelFieldArray))
    
    return fieldList
}

fun fetchIds(dtoClass: Class<*>, dto: DTO): List<Any?> {
    
    val idList = ArrayList<Any?>()
    
    if (dtoClass.superclass != null) {
        idList.addAll(fetchIds(dtoClass.superclass, dto))
    }
    
    idList.addAll(dtoClass.declaredFields
            .asSequence()
            .filter({ it.getAnnotation(Id::class.java) != null })
            .map({ it.isAccessible = true; it.get(dto) })
            .toList<Any?>())
    
    return idList
}

@Throws(SQLException::class, IllegalAccessException::class)
fun setFields(dto: Any, fieldList: List<Field>, resultSet: ResultSet) {
    for (field in fieldList) {
        val column: Column? = field.getAnnotation(Column::class.java)
        if (column != null) {
            field.isAccessible = true
            val value: Any?
            val fieldType = field.type
            value = try {
                if (fieldType == Int::class.javaPrimitiveType || fieldType == Integer::class.java) {
                    resultSet.getInt(column.value)
                } else if (fieldType == Long::class.javaPrimitiveType || fieldType == Long::class.java) {
                    resultSet.getLong(column.value)
                } else if (fieldType == Boolean::class.javaPrimitiveType || fieldType == Boolean::class.java) {
                    resultSet.getBoolean(column.value)
                } else if (fieldType == Timestamp::class.java) {
                    resultSet.getTimestamp(column.value)
                } else if (fieldType == Double::class.javaPrimitiveType || fieldType == Double::class.java) {
                    resultSet.getDouble(column.value)
                } else {
                    resultSet.getString(column.value)
                }
            } catch (e: Exception) {
                null
            }
            
            field.set(dto, value)
        }
    }
}