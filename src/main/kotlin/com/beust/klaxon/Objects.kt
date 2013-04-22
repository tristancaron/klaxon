package com.beust.klaxon

import java.util.Arrays

open public class JsonObject {
    val map = hashMapOf<String, JsonObject>()

    fun put(key: String, value: JsonObject) {
        map.put(key, value)
    }

    open fun toString() : String {
        return map.toString()
    }

    open fun asString() : String {
        throw RuntimeException("Not a String")
    }

    open fun asBoolean() : Boolean {
        throw RuntimeException("Not a Boolean")
    }

    open fun asDouble() : Double {
        throw RuntimeException("Not a Double")
    }

    open fun asLong() : Long {
        throw RuntimeException("Not a Long")
    }

    open fun asList() : List<JsonObject> {
        throw RuntimeException("Not an array")
    }

    fun get(key : String) : JsonObject? {
        return map.get(key)
    }
}

public class JsonString(val value: String) : JsonObject() {
    override fun asString() : String {
        return value
    }

    override fun toString() : String {
        return "{String: \"$value\"}"
    }

    fun equals(other : Any?) : Boolean {
        return (other as JsonString).value.equals(value)
    }

    fun hashCode() : Int {
        return value.hashCode()
    }
}

public class JsonLong(val value: Long) : JsonObject() {
    override fun asLong() : Long {
        return value
    }

    override fun toString() : String {
        return "{Long: $value}"
    }

    fun equals(other : Any?) : Boolean {
        return (other as JsonLong).value.equals(value)
    }

    fun hashCode() : Int {
        return value.hashCode()
    }
}

public class JsonDouble(val value: Double): JsonObject() {
    override open fun asDouble() : Double {
        return value
    }

    override fun toString() : String {
        return "{Double: $value}"
    }

    fun equals(other : Any?) : Boolean {
        return (other as JsonDouble).value.equals(value)
    }

    fun hashCode() : Int {
        return value.hashCode()
    }
}

public class JsonBoolean(val value: Boolean) : JsonObject() {
    override open fun asBoolean() : Boolean {
        return value
    }

    override fun toString() : String {
        return "{Boolean: $value}"
    }

    fun equals(other : Any?) : Boolean {
        return (other as JsonBoolean).value == value
    }

    fun hashCode() : Int {
        return value.hashCode()
    }
}

public class JsonArray() : JsonObject() {
    val value = arrayListOf<JsonObject>()

    fun add(o : JsonObject) : JsonArray {
        value.add(o)
        return this
    }

    override fun asList() : List<JsonObject> {
        return value
    }

    override fun toString() : String {
        val result = "{Array: " + value.toString() + "}"
        return result
    }

    fun equals(other : Any?) : Boolean {
        return (other as JsonArray).value.equals(value)
    }

    fun hashCode() : Int {
        return value.hashCode()
    }
}