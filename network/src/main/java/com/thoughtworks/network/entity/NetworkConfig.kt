package com.thoughtworks.network.entity

data class NetworkConfig(
    val timeOut: Long = 0L,
    val hostName: String,
    val envName: String
)