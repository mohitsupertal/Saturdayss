package com.shock.saturdaylifestyle.di

import com.shock.saturdaylifestyle.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideOKHttp() : OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient) : Retrofit =
            Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.saturdayseyewear.com/api/v1/")
            .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesRetrofit(retrofit: Retrofit) = ApiService(retrofit)


    @Singleton
    @Provides
    fun providesApiService(apiService: ApiService) = apiService.dataService
}