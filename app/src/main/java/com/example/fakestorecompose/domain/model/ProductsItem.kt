package com.example.fakestorecompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

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
) : Serializable {
    override fun hashCode(): Int {
        var result = id.hashCode()
        if(image.isEmpty()){
            result = 31 * result + image.hashCode()
        }
        return result
    }
}