package com.mx.android.network.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mx.android.common.constants.Generals
import com.mx.android.network.BuildConfig
import com.mx.android.network.HeroAppService
import com.mx.android.network.interceptors.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val CLIENT_HTTP_EATER = "OkHttpClient"
    }

    @Provides
    @Singleton
    fun networkServiceProvider(
        @Named(CLIENT_HTTP_EATER) okHttpClient: OkHttpClient
    ): HeroAppService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroAppService::class.java)


    @Provides
    @Named(CLIENT_HTTP_EATER)
    @Singleton
    fun okHttpClientProvider() = HttpLoggingInterceptor().run {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder()
            .connectTimeout(Generals.NetworkConstants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Generals.NetworkConstants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Generals.NetworkConstants.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addNetworkInterceptor(AuthorizationInterceptor())
            .build()
    }

}