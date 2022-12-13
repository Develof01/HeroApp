package com.mx.android.common.extensionFunctions

import com.mx.android.domain.dto.response.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> toResultFlow(invoke: suspend () -> Response<T>?): Flow<NetworkResult<T>> {
    return flow {
        emit(NetworkResult.Loading(isLoading = true))
        try {
            val response = invoke()
            response?.let {
                emit(NetworkResult.Loading(isLoading = false))
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(NetworkResult.Success(body))
                    } ?: kotlin.run {
                        val errorMsg = response.errorBody()?.string()
                        response.errorBody()?.close()
                        emit(NetworkResult.Error(exception = errorMsg ?: ""))
                    }
                }
            }
        } catch (throwable: Throwable) {
            emit(NetworkResult.Loading(isLoading = false))
            if (throwable is HttpException) {
                emit(NetworkResult.Error(
                    code =  throwable.code(),
                    exception = throwable.message ?: throwable.cause.toString()))
            } else {
                emit(NetworkResult.Error(exception = throwable.message ?: throwable.cause.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)
}