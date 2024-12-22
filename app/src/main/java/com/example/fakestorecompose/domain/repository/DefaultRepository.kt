package com.example.fakestorecompose.domain.repository

import com.example.fakestorecompose.data.remote.dto.DefaultApi
import com.example.fakestorecompose.util.ApiState
import kotlinx.coroutines.flow.Flow

interface DefaultRepository {
    fun getDefaultApiData() : Flow<ApiState<DefaultApi?>>
}