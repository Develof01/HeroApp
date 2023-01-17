package com.mx.android.data.datasource.local

import com.mx.android.database.entities.Hero
import kotlinx.coroutines.flow.Flow

interface IHeroLocalDataSource {

    suspend fun getAllHeros(): Flow<List<Hero>?>

    suspend fun getFavoriteStateByHero(id: Int): Flow<Boolean>

    suspend fun insertHerosList(herosEntity: List<Hero>)

    suspend fun updateHeroFavoriteState(hero: Hero)

}