package com.thoughtworks.android.core.extensions

val <T> T.isNull: Boolean
    get() {
        return this == null
    }

val <T> T.isNotNull: Boolean
    get() {
        return this != null
    }


fun <T : Any> T?.orElse(item: T) =
    this ?: item

fun <T : Any> T?.or(item: T?) =
    this ?: item


fun <T : CharSequence> T?.orElse(item: T) =
    if (this != null && isNotBlank()) this else item

fun <T : CharSequence> T?.or(item: T?) =
    if (this != null && isNotBlank()) this else item

fun <T : Number> T?.orElse(number: T) =
    if (this != null && this != 0) this else number

fun <T : Number> T?.or(number: T?) =
    if (this != null && this == 0) this else number

fun <T> List<T>?.orElse(list: List<T>) =
    if (this != null && isNotEmpty()) this else list

fun <T> List<T>?.or(list: List<T>?) =
    if (this != null && isNotEmpty()) this else list


fun <K, V> Map<K, V>?.orElse(map: Map<K, V>) =
    if (this != null && isNotEmpty()) this else map

fun <K, V> Map<K, V>?.or(map: Map<K, V>?) =
    if (this != null && isNotEmpty()) this else map