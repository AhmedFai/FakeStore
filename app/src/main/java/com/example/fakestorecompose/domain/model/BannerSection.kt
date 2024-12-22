package com.example.fakestorecompose.domain.model

data class BannerSection(
    val id: Int,
    val items: List<Item>,
    val position: Int,
    val title: String
)