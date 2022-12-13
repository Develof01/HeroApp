package com.mx.android.data.mappers.hero

import com.mx.android.domain.modules.Hero as HeroDomain
import com.mx.android.domain.dto.response.hero.Hero as HeroDto

fun List<HeroDto>.asDomain(): List<HeroDomain>  = map {
        HeroDomain (
            id = it.id,
            name = it.name,
            description = it.description,
            thumbnail = it.thumbnail)
}