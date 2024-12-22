package com.example.fakestorecompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestorecompose.domain.usecases.defaultapi.DefaultApiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DefaultApiViewModel @Inject constructor(
    private val defaultApiUseCases: DefaultApiUseCases
) : ViewModel() {

    val defaultApiData = defaultApiUseCases.getDefaultData()

}