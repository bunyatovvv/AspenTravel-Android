package com.behmennnn.aspentravel.di

import com.behmennnn.aspentravel.common.util.BASE_URL
import com.behmennnn.aspentravel.data.service.Api
import com.behmennnn.aspentravel.data.repository.ApiRepoImpl
import com.behmennnn.aspentravel.data.source.ApiSource
import com.behmennnn.aspentravel.data.source.ApiSourceImpl
import com.behmennnn.aspentravel.domain.repository.ApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi() = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(Api::class.java)

    @Singleton
    @Provides
    fun provideApiSource(api: Api) = ApiSourceImpl(api) as ApiSource

    @Singleton
    @Provides
    fun provideApiRepo(apiSource: ApiSource) = ApiRepoImpl(apiSource) as ApiRepo

}