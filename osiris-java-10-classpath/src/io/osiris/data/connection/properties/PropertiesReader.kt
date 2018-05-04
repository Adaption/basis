package io.osiris.data.connection.properties

import io.osiris.data.connection.ConnectionReader
import java.io.File
import java.io.FileNotFoundException
import java.util.*

object PropertiesReader : ConnectionReader {
    override fun getName(): String = getProperties().getProperty("database.name")
    
    override fun getDriverClass(): String = getProperties().getProperty("database.driver")
    
    override fun getUrl(): String = getProperties().getProperty("database.url")
    
    override fun getUsername(): String = getProperties().getProperty("database.username")
    
    override fun getPassword(): String = getProperties().getProperty("database.password")
}

private fun getProperties(): Properties = try {
    val inputStream = File("resources/connection.properties").inputStream()
    val properties = Properties()
    
    properties.load(inputStream)
    properties
} catch (e: FileNotFoundException) {
    Properties()
}