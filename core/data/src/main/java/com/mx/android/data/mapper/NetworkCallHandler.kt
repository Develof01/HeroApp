package com.mx.android.data.mapper

import com.mx.android.domain.modules.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> callNewRequest(invoke: suspend () -> Response<T>?): Flow<NetworkResult<T>> {
    return flow {
        try {
            val response = invoke()
            response?.let {
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(NetworkResult.Success(it))
                    } ?: kotlin.run {
                        val errorMsg = response.errorBody()?.string()
                        emit(NetworkResult.Error(exception = errorMsg ?: ""))
                    }
                }
            }
        } catch (throwable: Throwable) {
            if (throwable is HttpException) {
                emit(
                    NetworkResult.Error(
                    code =  throwable.code(),
                    exception = throwable.message ?: throwable.cause.toString()))
            } else {
                emit(NetworkResult.Error(exception = throwable.message ?: throwable.cause.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)
}