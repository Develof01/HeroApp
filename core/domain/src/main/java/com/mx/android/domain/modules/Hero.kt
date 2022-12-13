package com.mx.android.domain.modules

import com.mx.android.domain.dto.response.hero.HeroThumbnail

data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail
)
