package com.example.fakestorecompose.presentation.storeNavigator

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fakestorecompose.R
import com.example.fakestorecompose.domain.model.ProductsItem
import com.example.fakestorecompose.presentation.details.DetailsScreen
import com.example.fakestorecompose.presentation.home.DefaultApiViewModel
import com.example.fakestorecompose.presentation.home.HomeScreen
import com.example.fakestorecompose.presentation.home.common.CustomToolbar
import com.example.fakestorecompose.presentation.home.common.ToolbarAction
import com.example.fakestorecompose.presentation.navGraph.Route
import com.example.fakestorecompose.presentation.storeNavigator.component.StoreBottomNavigation
import com.example.fakestorecompose.presentation.storeNavigator.component.StoreBottomNavigationItem

@SuppressLint("RememberReturnType")
@Composable
fun StoreNavigator(){

    val bottomNavigationItems = remember {
        listOf(
            StoreBottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            StoreBottomNavigationItem(icon = R.drawable.ic_categories, text = "Category"),
            StoreBottomNavigationItem(icon = R.drawable.ic_wishlist, text = "Wishlist"),
            StoreBottomNavigationItem(icon = R.drawable.shopping_cart, text = "Cart"),
            StoreBottomNavigationItem(icon = R.drawable.ic_profile, text = "Profile")
        )
    }

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    // Type-safe route matching
    val currentRoute = backStackEntry?.destination?.route

    selectedItem = when (currentRoute) {
        Route.HomeScreen.routeName -> 0
        Route.CategoryScreen.routeName -> 1
        Route.WishlistScreen.routeName -> 2
        Route.CartScreen.routeName -> 3
        Route.ProfileScreen.routeName -> 4
        else -> 0
    }

    // Determine bottom bar visibility
    val isBottomBarVisible = currentRoute == Route.HomeScreen.routeName ||
            currentRoute == Route.CategoryScreen.routeName ||
            currentRoute == Route.WishlistScreen.routeName ||
            currentRoute == Route.CartScreen.routeName ||
            currentRoute == Route.ProfileScreen.routeName

    val isBottomTab = currentRoute in listOf(
        Route.HomeScreen.routeName,
        Route.CategoryScreen.routeName,
        Route.WishlistScreen.routeName,
        Route.CartScreen.routeName,
        Route.ProfileScreen.routeName
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (isBottomTab){
                CustomToolbar(
                    title = bottomNavigationItems[selectedItem].text,
                    showBackButton = false,
                    actions = getToolbarActionsForRoute(currentRoute)
                )
            }
        },
        bottomBar = {
            if (isBottomBarVisible) {
                StoreBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.CategoryScreen
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.WishlistScreen
                            )

                            3 -> navigateToTab(
                                navController = navController,
                                route = Route.CartScreen
                            )

                            4 -> navigateToTab(
                                navController = navController,
                                route = Route.ProfileScreen
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        val bottomPadding = innerPadding.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.routeName
        ) {
            //storeGraph(navController)
            composable(Route.HomeScreen.routeName){
                val viewMode: DefaultApiViewModel = hiltViewModel()
                val data = viewMode.defaultApiData.collectAsState(initial = null)
                Log.e("DefaultApi", viewMode.defaultApiData.toString())
                Log.e("DefaultApiData", data.value.toString())

                data.value?.let { it1 ->
                    HomeScreen(
                        Modifier.padding(innerPadding),
                        apiState = it1,
                        navigateToCategory = {
//                            navigateToTab(
//                                navController = navController,
//                                route = Route.CategoryScreen
//                            )
                        },
                        navigateToDetails = {
                            navigateToDetails(
                                navController = navController,
                                product = it
                            )
                        }
                    )
                }
            }

            composable(Route.CategoryScreen.routeName){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(text = "Category Screen",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
            }

            composable(Route.WishlistScreen.routeName){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(text = "Wishlist Screen",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
            }

            composable(Route.ProductsScreen.routeName){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(text = "Products Screen",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
            composable(Route.ProductDetailScreen.routeName){
                navController.previousBackStackEntry?.savedStateHandle?.get<ProductsItem>("product")
                    ?.let { product ->
                        DetailsScreen(
                            product,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }
            composable(Route.CartScreen.routeName){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(text = "Cart Screen",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
            composable(Route.ProfileScreen.routeName){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(text = "Profile Screen",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        },
        Modifier.background(color = MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .shadow(10.dp),

    )
}

private fun navigateToTab(navController: NavController, route: Route) {
    navController.navigate(route.routeName) {
        navController.graph.startDestinationRoute?.let { homeRoute ->
            popUpTo(homeRoute) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}


private fun navigateToDetails(navController: NavController, product: ProductsItem) {
    navController.currentBackStackEntry?.savedStateHandle?.set("product", product)
    navController.navigate(
        route = Route.ProductDetailScreen.routeName
    )
}

fun getToolbarActionsForRoute(route: String?): List<ToolbarAction> {
    return when (route) {
        Route.HomeScreen.routeName -> listOf(
            ToolbarAction(Icons.Default.Search, "Search", onClick = {}),
            ToolbarAction(Icons.Default.Notifications, "Notifications", onClick = {}),
            ToolbarAction(Icons.Default.ShoppingCart, "Cart", onClick = {})
        )

        Route.CategoryScreen.routeName -> listOf(
            ToolbarAction(Icons.Default.FavoriteBorder, "Cart", onClick = {})
        )
        else -> emptyList()
    }
}