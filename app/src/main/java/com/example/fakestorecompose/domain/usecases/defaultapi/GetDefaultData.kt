package com.example.fakestorecompose.domain.usecases.defaultapi

import com.example.fakestorecompose.data.remote.dto.DefaultApi
import com.example.fakestorecompose.domain.model.ProductsItem
import com.example.fakestorecompose.domain.repository.DefaultRepository
import com.example.fakestorecompose.util.ApiState
import kotlinx.coroutines.flow.Flow

class GetDefaultData(
    private val defaultRepository: DefaultRepository
) {

    operator fun invoke() : Flow<ApiState<DefaultApi?>> {
        return defaultRepository.getDefaultApiData()
    }

}