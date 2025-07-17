package com.example.fakestorecompose.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakestorecompose.R
import com.example.fakestorecompose.data.remote.dto.DefaultApi
import com.example.fakestorecompose.domain.model.Categories
import com.example.fakestorecompose.domain.model.ProductsItem
import com.example.fakestorecompose.ui.theme.Roboto
import com.example.fakestorecompose.util.ApiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    apiState: ApiState<DefaultApi?>,
    navigateToCategory: (Categories) -> Unit,
    navigateToDetails: (ProductsItem) -> Unit
){
    when(apiState){
        is ApiState.Error ->{}
        is ApiState.Exception -> {}
        is ApiState.Loading -> {}
        is ApiState.Success -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
            ) {
                item {
                    BannerSection(
                        images = apiState.data?.bannerSection?.items?.map { it.image }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = "Categories",
                        fontSize = 18.sp,
                        fontFamily = Roboto,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                        color = colorResource(id = R.color.text_title),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CategoriesSection(data = apiState.data?.categories?.items!!, onClick = {})
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = "Trending Products",
                        fontSize = 18.sp,
                        fontFamily = Roboto,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                        color = colorResource(id = R.color.text_title),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    TrendingSection(
                        trendingProducts = apiState.data!!.trendingProducts.items,
                        onClick = {
                            navigateToDetails(it)
                        })
                }
                item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    text = "Featured Products",
                    fontSize = 18.sp,
                    fontFamily = Roboto,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.text_title),
                )
                Spacer(modifier = Modifier.height(12.dp))
                FeaturedSection(
                    featuredProducts = apiState.data!!.featuredProducts.items,
                    onClick = {
                        navigateToDetails(it)
                    })
            }
                item{
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = "Sale Products",
                        fontSize = 18.sp,
                        fontFamily = Roboto,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                        color = colorResource(id = R.color.text_title),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SaleSection(
                        saleProducts = apiState.data!!.saleProducts.items,
                        onClick = {
                            navigateToDetails(it)
                        })
                }
            }
        }
    }
}

