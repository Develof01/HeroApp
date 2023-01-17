package com.mx.android.network.dto.response.hero

data class HeroResponse(
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Hero>
)
