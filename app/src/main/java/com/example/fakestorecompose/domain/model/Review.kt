package com.example.fakestorecompose.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class Review(
    val name: String,
    val date: String,
    val rating: Int,
    val reviewText: String,
    val likes: Int,
    val comments: Int,
    val profileImage: Painter
)
