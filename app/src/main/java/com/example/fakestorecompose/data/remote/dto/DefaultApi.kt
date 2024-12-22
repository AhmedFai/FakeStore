package com.example.fakestorecompose.data.remote.dto

import com.example.fakestorecompose.domain.model.BannerSection
import com.example.fakestorecompose.domain.model.Categories
import com.example.fakestorecompose.domain.model.FeaturedProducts
import com.example.fakestorecompose.domain.model.SaleProducts
import com.example.fakestorecompose.domain.model.TrendingProducts

data class DefaultApi(
    val bannerSection: BannerSection,
    val categories: Categories,
    val featuredProducts: FeaturedProducts,
    val saleProducts: SaleProducts,
    val trendingProducts: TrendingProducts
)
