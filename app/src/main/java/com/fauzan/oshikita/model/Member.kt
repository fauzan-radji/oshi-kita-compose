package com.fauzan.oshikita.model

data class Member(
    val id: Int,
    val name: String,
    val nicknames: List<String>,
    val fanbase: String,
    val generation: Int,
    val jiko: String,
    val description: String,
    val photoUrl: String,
    var isOshi: Boolean = false
)