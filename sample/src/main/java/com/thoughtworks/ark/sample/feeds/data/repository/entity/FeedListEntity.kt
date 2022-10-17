package com.thoughtworks.ark.sample.feeds.data.repository.entity

import kotlinx.serialization.Serializable

@Serializable
data class FeedListEntity(
    val errorCode: Int = -1,
    val errorMsg: String? = null,
    val data: List<FeedEntity> = emptyList(),
)

@Serializable
data class FeedEntity(
    val id: Int,
    val name: String,
    val link: String,
    val visible: Int,
    val order: Int,
    val icon: String,
    val category: String,
)