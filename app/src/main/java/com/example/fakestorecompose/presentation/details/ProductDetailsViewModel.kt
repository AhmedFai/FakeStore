package com.example.fakestorecompose.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fakestorecompose.domain.model.ProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle : SavedStateHandle
) : ViewModel() {

    private val _product = MutableStateFlow<ProductsItem?>(null)
    val product : StateFlow<ProductsItem?> = _product

    init {
        _product.value = savedStateHandle.get("product")
    }

}