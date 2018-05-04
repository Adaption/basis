@file:JvmName("RelationBindingHandler")

package io.osiris.data.common.binding.function

import io.osiris.data.common.annotation.ManyToOne
import io.osiris.data.common.annotation.OneToMany

import java.lang.reflect.Method
import java.util.*

fun fetchManyToOne(method: Method): Map<String, String> {
    val relationMap = HashMap<String, String>()
    
    val annotation = method.getAnnotation(ManyToOne::class.java)
    
    relationMap["column"] = annotation.column
    relationMap["table"] = annotation.table
    relationMap["target"] = annotation.target
    
    return relationMap
}

fun fetchOneToMany(method: Method): Map<String, String> {
    val relationMap = HashMap<String, String>()
    
    val annotation = method.getAnnotation(OneToMany::class.java)
    
    relationMap["table"] = annotation.table
    relationMap["column"] = annotation.column
    relationMap["target"] = annotation.target
    
    return relationMap
}