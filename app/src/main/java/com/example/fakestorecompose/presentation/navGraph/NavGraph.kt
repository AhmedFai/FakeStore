package com.example.fakestorecompose.presentation.navGraph


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.fakestorecompose.presentation.onboarding.OnBoardingScreen
import com.example.fakestorecompose.presentation.onboarding.OnBoardingViewModel
import com.example.fakestorecompose.presentation.storeNavigator.StoreNavigator

@Composable
fun navGraph(
    startDestination: Route
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination.routeName
    ) {
        //onBoardingGraph(navController)
        //storeGraph(navController)

        navigation(startDestination = Route.OnBoardingScreen.routeName,
            route = Route.AppStartNavigation.routeName){
            composable(Route.OnBoardingScreen.routeName) {
                val viewModel = hiltViewModel<OnBoardingViewModel>()
                OnBoardingScreen(viewModel::onEvent)
            }
        }

        navigation(startDestination = Route.StoreNavigatorScreen.routeName,
            route = Route.StoreNavigation.routeName){
            composable(Route.StoreNavigatorScreen.routeName) {
               StoreNavigator()
            }
        }
    }
}