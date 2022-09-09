package com.thoughtworks.ark.ui.home.feeds.data.repository.entity

import kotlinx.serialization.Serializable

@Serializable
data class FeedListEntity(
    val errorCode: Int,
    val errorMsg: String?,
    val data: List<FeedEntity>?,
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