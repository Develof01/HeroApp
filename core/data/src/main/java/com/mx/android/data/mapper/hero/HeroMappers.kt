package com.mx.android.data.mapper.hero

import com.mx.android.domain.modules.hero.HeroThumbnail
import com.mx.android.network.dto.response.hero.Hero as HeroDto
import com.mx.android.database.entities.Hero as HeroDB
import com.mx.android.domain.modules.hero.Hero as HeroDomain

fun List<HeroDto>.dtoToDomain(): List<HeroDomain>  = map {
    HeroDomain(
        id = it.id,
        name = it.name,
        description = it.description,
        thumbnail = HeroThumbnail(
            it.thumbnail.path,
            it.thumbnail.extension
        )
    )
}

fun List<HeroDB>.bdToDomain(): List<HeroDomain> = map {
    HeroDomain(
        id = it.uid,
        name = it.firstName ?: "",
        description = it.description ?: "",
        thumbnail = HeroThumbnail(
            it.imagePath ?: "",
            it.imageExtension ?: ""
        ),
        isFavorite = it.isFavorite
    )
}

fun List<HeroDomain>.domainToDb(): List<HeroDB> = map {
    HeroDB(
        uid = it.id,
        firstName = it.name,
        description = it.description,
        imagePath = it.thumbnail.path,
        imageExtension = it.thumbnail.extension,
        isFavorite = it.isFavorite
    )
}

fun HeroDomain.domainToDb(): HeroDB =
    HeroDB(
        uid = this.id,
        firstName = this.name,
        description = this.description,
        imagePath = this.thumbnail.path,
        imageExtension = this.thumbnail.extension,
        isFavorite = this.isFavorite
    )

