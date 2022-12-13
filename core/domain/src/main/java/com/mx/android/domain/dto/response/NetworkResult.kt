package com.mx.android.domain.dto.response

sealed class NetworkResult <out T> (
    val status: ApiStatus,
    val data: T?,
    val error: NetworkError?) {

    data class Success<out R>(val _data: R?): NetworkResult<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        error = null
    )

    data class Error(val code: Int? = 0, val exception: String): NetworkResult<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        error = NetworkError(code, exception)
    )

    data class Loading(val isLoading: Boolean): NetworkResult<Nothing>(
        status = ApiStatus.LOADING,
        data = null,
        error = null
    )
}
