package com.example.coordinatorlayout

import java.io.Serializable

data class Movie(
    val id: Long = 0L,
    val imageUrl: String = "",
    val title: String = "",
    val description: String = "",
    var isFavorite: Boolean = false
): Serializable
