package com.mx.android.domain.dto.response

data class HeroResponse(
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Hero>
)