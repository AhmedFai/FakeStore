package com.example.fakestorecompose.domain.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "products")
data class ProductsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Parcelable