package com.mx.android.data.repository

import com.mx.android.data.datasource.local.IHeroLocalDataSource
import com.mx.android.data.datasource.network.IHeroDataSource
import com.mx.android.data.mapper.hero.bdToDomain
import com.mx.android.data.mapper.hero.domainToDb
import com.mx.android.data.mapper.hero.dtoToDomain
import com.mx.android.domain.modules.hero.Hero
import com.mx.android.domain.repository.hero.IHeroRepository
import com.mx.android.domain.modules.network.NetworkResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val networkIHeroDataSource: IHeroDataSource,
    private val localIHeroDataSource: IHeroLocalDataSource
) : IHeroRepository {

    override suspend fun getHeroList(): Flow<NetworkResult<List<Hero>>> = flow {
        networkIHeroDataSource.getHeroList()
            .catch { e -> emit(NetworkResult.Error(e.hashCode(), e.message ?: "")) }
            .transform { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            emit(NetworkResult.Success(it.apiData.results.dtoToDomain()))
                        }
                    }
                    else -> {
                        emit(
                            NetworkResult.Error(
                                response.error?.code,
                                response.error?.message ?: ""
                            )
                        )
                    }
                }
            }
            .collect { response ->
                emit(response)
            }
    }

    override suspend fun getLocalHeroList(): Flow<NetworkResult<List<Hero>?>> = flow {
        localIHeroDataSource.getAllHeros()
            .catch { e -> emit(NetworkResult.Error(e.hashCode(), e.message ?: "")) }
            .transform { heros -> emit(NetworkResult.Success(heros?.bdToDomain())) }
            .collect { localHeros -> emit(localHeros) }
    }

    override suspend fun getLocalFavoriteState(id: Int): Flow<Boolean> {
        return localIHeroDataSource.getFavoriteStateByHero(id)
    }

    override suspend fun insertLocalHeroList(heros: List<Hero>) {
        localIHeroDataSource.insertHerosList(heros.domainToDb())
    }

    override suspend fun updateLocalHeroFavoriteState(hero: Hero) {
        localIHeroDataSource.updateHeroFavoriteState(hero.domainToDb())
    }

}