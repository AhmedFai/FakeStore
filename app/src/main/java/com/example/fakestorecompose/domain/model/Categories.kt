package com.example.fakestorecompose.domain.model

data class Categories(
    val id: Int,
    val items: List<ItemX>,
    val position: Int,
    val title: String
)