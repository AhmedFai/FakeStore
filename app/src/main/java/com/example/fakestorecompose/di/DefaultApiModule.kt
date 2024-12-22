package com.example.fakestorecompose.di

import com.example.fakestorecompose.data.remote.DefaultApiService
import com.example.fakestorecompose.data.remote.repository.DefaultRepositoryImpl
import com.example.fakestorecompose.domain.repository.DefaultRepository
import com.example.fakestorecompose.domain.usecases.defaultapi.DefaultApiUseCases
import com.example.fakestorecompose.domain.usecases.defaultapi.GetDefaultData
import com.example.fakestorecompose.util.Constants.BASE_URL_
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DefaultApiModule {

    @Singleton
    @Provides
    @Named("defaultApi")
    fun provideDefaultApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("defaultApiService")
    fun provideApiService2(@Named("defaultApi") retrofit: Retrofit): DefaultApiService {
        return retrofit.create(DefaultApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideDefaultRepository(
        @Named("defaultApiService") defaultApiService: DefaultApiService
    ): DefaultRepository = DefaultRepositoryImpl(
        defaultApis = defaultApiService
    )

    @Singleton
    @Provides
    fun providesDefaultUseCases(
        defaultApiRepository: DefaultRepository
    ): DefaultApiUseCases {
        return DefaultApiUseCases(
            getDefaultData = GetDefaultData(defaultApiRepository)
        )
    }

}