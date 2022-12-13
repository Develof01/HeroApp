package com.mx.android.domain.dto.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    val code: Int,
    val status: String,
    @SerializedName("data")
    val apiData: T
)
