package com.example.fakestorecompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestorecompose.domain.usecases.appentry.AppEntryUseCases
import com.example.fakestorecompose.presentation.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {
    var splashScreenCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf<Route>(Route.AppStartNavigation)
        private set


    init {
        appEntryUseCases.readAppEntry().onEach {
            startDestination = if (it) {
                Route.StoreNavigation
            } else {
                Route.AppStartNavigation
            }
            delay(300)
            splashScreenCondition = false
        }.launchIn(viewModelScope)
    }

}