package com.mx.android.network.interceptors

import com.mx.android.common.constants.Generals
import com.mx.android.common.utils.MDHandle
import com.mx.android.network.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthorizationInterceptor: Interceptor {

    companion object {
        const val HASH_PARAMETER = "hash"
        const val TIME_STAMP_PARAMETER = "ts"
        const val API_KEY_PARAMETER = "apikey"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val inputSB = StringBuilder()
        inputSB.append(Generals.NetworkConstants.TS)
        inputSB.append(BuildConfig.API_KEY)
        inputSB.append(BuildConfig.API_PRIVATE_KEY)

        val hash: String = MDHandle().md5(inputSB.toString())

        var request: Request = chain.request()
        val url: HttpUrl = request.url
            .newBuilder()
            .addQueryParameter(TIME_STAMP_PARAMETER, Generals.NetworkConstants.TS)
            .addQueryParameter(API_KEY_PARAMETER, BuildConfig.API_KEY)
            .addQueryParameter(HASH_PARAMETER, hash)
            .build()

        request = request
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}