package com.example.fakestorecompose.data.remote

import com.example.fakestorecompose.data.remote.dto.DefaultApi
import retrofit2.Response
import retrofit2.http.GET

interface DefaultApiService {
    @GET("b/655dde4e12a5d376599cfbe5?meta=false")
    suspend fun getDefaultApiData() : Response<DefaultApi>
}