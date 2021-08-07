package com.example.unravel.di

import com.example.unravel.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideOKHttp() : OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient) : Retrofit =
            Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://vidzee.s3.ap-south-1.amazonaws.com/data/content/html/generated_v2/ACTIVITY/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    @Provides
    fun providesRetrofit(retrofit: Retrofit) = ApiService(retrofit)


    @Singleton
    @Provides
    fun providesApiService(apiService: ApiService) = apiService.dataService
}