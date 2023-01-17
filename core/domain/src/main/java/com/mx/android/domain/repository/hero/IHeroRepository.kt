package com.mx.android.domain.repository.hero

import com.mx.android.domain.modules.hero.Hero
import com.mx.android.domain.modules.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IHeroRepository {
    suspend fun getHeroList(): Flow<NetworkResult<List<Hero>>>
    suspend fun getLocalHeroList(): Flow<NetworkResult<List<Hero>?>>
    suspend fun getLocalFavoriteState(id: Int): Flow<Boolean>
    suspend fun insertLocalHeroList(heros: List<Hero>)
    suspend fun updateLocalHeroFavoriteState(hero: Hero)
}