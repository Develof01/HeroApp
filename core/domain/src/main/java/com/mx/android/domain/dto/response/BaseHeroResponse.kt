package com.mx.android.domain.dto.response

data class BaseHeroResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)
