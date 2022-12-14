package com.mx.android.data.mappers.hero

import com.mx.android.domain.dto.response.BaseResponse
import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.dto.response.hero.Hero
import com.mx.android.domain.dto.response.hero.HeroResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun List<Hero>.asDomain(): List<com.mx.android.domain.modules.Hero>  = map {
    com.mx.android.domain.modules.Hero(
        id = it.id,
        name = it.name,
        description = it.description,
        thumbnail = it.thumbnail
    )
}