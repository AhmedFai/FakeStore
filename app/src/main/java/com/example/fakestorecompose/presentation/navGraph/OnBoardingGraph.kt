package com.example.fakestorecompose.presentation.navGraph

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.fakestorecompose.presentation.onboarding.OnBoardingScreen
import com.example.fakestorecompose.presentation.onboarding.OnBoardingViewModel

@SuppressLint("RestrictedApi")
fun NavGraphBuilder.onBoardingGraph(navController: NavController) {

    navigation<Route.AppStartNavigation>(startDestination = Route.OnBoardingScreen){
        composable<Route.OnBoardingScreen> {
            val viewModel = hiltViewModel<OnBoardingViewModel>()
            OnBoardingScreen(viewModel::onEvent)
        }
    }
}