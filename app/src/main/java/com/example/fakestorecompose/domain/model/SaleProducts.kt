package com.example.fakestorecompose.domain.model

data class SaleProducts(
    val id: Int,
    val items: List<ProductsItem>,
    val position: Int,
    val title: String
)