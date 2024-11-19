package com.example.composebasics.data

data class StackAnswersPojo(
    val backoff: Int,
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)
data class Item(
    val content_license: String,
    val creation_date: Int,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: Owner,
    val post_id: Int,
    val post_type: String,
    val score: Int
)
data class Owner(
    val accept_rate: Int,
    val display_name: String,
    val link: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String
)