package com.example.fakestorecompose.domain.model

data class TrendingProducts(
    val id: Int,
    val items: List<ProductsItem>,
    val position: Int,
    val title: String
)