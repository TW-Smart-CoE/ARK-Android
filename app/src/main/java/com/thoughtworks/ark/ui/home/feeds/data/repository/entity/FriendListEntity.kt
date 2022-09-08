package com.thoughtworks.ark.ui.home.feeds.data.repository.entity

import kotlinx.serialization.Serializable

@Serializable
data class FriendListEntity(
    val errorCode: Int,
    val errorMsg: String?,
    val data: List<FriendEntity>?,
)

@Serializable
data class FriendEntity(
    val id: Int,
    val name: String,
    val link: String,
    val visible: Int,
    val order: Int,
    val icon: String,
    val category: String,
)