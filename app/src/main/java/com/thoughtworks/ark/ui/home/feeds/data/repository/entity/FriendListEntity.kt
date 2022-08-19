package com.thoughtworks.ark.ui.home.feeds.data.repository.entity

data class FriendListEntity(
    var errorCode: Int,
    var errorMsg: String?,
    var data: List<FriendEntity>?
)

data class FriendEntity(
    var id: Int,
    var name: String,
    var link: String,
    var visible: Int,
    var order: Int,
    var icon: Any
)