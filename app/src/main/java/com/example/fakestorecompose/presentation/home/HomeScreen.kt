package com.example.fakestorecompose.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fakestorecompose.data.remote.dto.DefaultApi
import com.example.fakestorecompose.domain.model.Categories
import com.example.fakestorecompose.util.ApiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    apiState: ApiState<DefaultApi?>,
    navigateToCategory: (Categories) -> Unit
){

    when(apiState){
        is ApiState.Error ->{}
        is ApiState.Exception -> {}
        is ApiState.Loading -> {}
        is ApiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                BannerSection(
                    images = apiState.data?.bannerSection?.items?.map { it.image }
                )
                Spacer(modifier = Modifier.height(24.dp))
                CategoriesSection(data = apiState.data?.categories?.items!!, onClick = {})
                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}

