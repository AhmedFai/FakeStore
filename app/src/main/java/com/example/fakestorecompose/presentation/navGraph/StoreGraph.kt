package com.example.fakestorecompose.presentation.navGraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.storeGraph(navController: NavController) {

    navigation<Route.StoreNavigation>(startDestination = Route.HomeScreen){
        composable<Route.HomeScreen>{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(text = "Home Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        composable<Route.CategoryScreen>{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(text = "Category Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
        composable<Route.ProductsScreen>{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(text = "Products Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
        composable<Route.ProductDetailScreen>{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(text = "Product Details Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
        composable<Route.CartScreen>{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(text = "Cart Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}