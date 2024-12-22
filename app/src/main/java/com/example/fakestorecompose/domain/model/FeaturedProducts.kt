package com.example.fakestorecompose.domain.model

data class FeaturedProducts(
    val id: Int,
    val items: List<ProductsItem>,
    val position: Int,
    val title: String
)