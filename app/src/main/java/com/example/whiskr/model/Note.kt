package com.example.whiskr.model

data class Note(
    val id: String = "",
    val userId: String = "",
    val content: String = "",
    val timestamp: Long = 0L
)
