package com.example.fakestorecompose.data.remote.repository

import com.example.fakestorecompose.data.remote.DefaultApiService
import com.example.fakestorecompose.data.remote.dto.DefaultApi
import com.example.fakestorecompose.domain.repository.DefaultRepository
import com.example.fakestorecompose.util.ApiState
import com.example.fakestorecompose.util.LogLevel
import com.example.fakestorecompose.util.handleError
import com.example.fakestorecompose.util.log
import com.example.fakestorecompose.util.relativeUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class DefaultRepositoryImpl @Inject constructor(private val defaultApis: DefaultApiService) : DefaultRepository {
    private val TAG = "log_" + DefaultRepositoryImpl::class.java.name.split(DefaultRepositoryImpl::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]
    override fun getDefaultApiData(): Flow<ApiState<DefaultApi?>> {
        return flow {
            emit(ApiState.Loading())
            try {
                val response : Response<DefaultApi> =defaultApis.getDefaultApiData()
                LogLevel.Info.log(TAG = "log_url", message = "getUserBanks: relativeUrl --> ${response.relativeUrl()}")
                val getDefaultApiResponse : DefaultApi? = response.body()
                val apiState: ApiState<DefaultApi> = if (response.isSuccessful && getDefaultApiResponse != null) {
                    ApiState.Success(getDefaultApiResponse)
                } else {
                    handleError(response.code(), response.errorBody()?.string())
                }
                emit(apiState)
            }catch (e : Exception){
                LogLevel.Error.log(TAG = TAG, message = "defaultApi: EXCEPTION --> ${e.message}")
                e.printStackTrace()
                emit(ApiState.Exception(e, null))
            }
        }.flowOn(Dispatchers.IO)
    }

}