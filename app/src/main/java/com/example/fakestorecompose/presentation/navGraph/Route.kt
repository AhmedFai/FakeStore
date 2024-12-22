package com.example.fakestorecompose.presentation.navGraph

import androidx.compose.runtime.State
import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable

sealed interface Route {
    val routeName: String
    @Serializable
    object OnBoardingScreen : Route {
        override val routeName: String
            get() = "onBoardingScreen"
    }
    @Serializable
    object HomeScreen : Route {
        override val routeName: String
            get() = "homeScreen"
    }
    @Serializable
    object CategoryScreen : Route {
        override val routeName: String
            get() = "categoryScreen"
    }
    @Serializable
    object WishlistScreen : Route {
        override val routeName: String
            get() = "wishlistScreen"
    }
    @Serializable
    object ProductsScreen : Route {
        override val routeName: String
            get() = "productsScreen"
    }
    @Serializable
    object ProductDetailScreen : Route {
        override val routeName: String
            get() = "productDetailScreen"
    }
    @Serializable
    object CartScreen : Route {
        override val routeName: String
            get() = "cartScreen"
    }
    @Serializable
    object ProfileScreen : Route {
        override val routeName: String
            get() = "profileScreen"
    }
    @Serializable
    object AppStartNavigation : Route {
        override val routeName: String
            get() = "appStartNavigation"
    }
    @Serializable
    object StoreNavigation : Route {
        override val routeName: String
            get() = "storeNavigation"
    }
    @Serializable
    object StoreNavigatorScreen : Route {
        override val routeName: String
            get() = "storeNavigatorScreen"
    }
}