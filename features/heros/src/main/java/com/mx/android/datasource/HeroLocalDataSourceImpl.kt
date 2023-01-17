package com.mx.android.datasource

import com.mx.android.data.datasource.local.IHeroLocalDataSource
import com.mx.android.database.dao.HeroDao
import com.mx.android.database.entities.Hero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeroLocalDataSourceImpl @Inject constructor(
    private val heroDao: HeroDao
): IHeroLocalDataSource {

    override suspend fun getAllHeros(): Flow<List<Hero>?> {
        return heroDao.findAll()
    }

    override suspend fun getFavoriteStateByHero(id: Int): Flow<Boolean> = heroDao.isFavorite(id)

    override suspend fun insertHerosList(herosEntity: List<Hero>) {
        heroDao.insertListHero(herosEntity)
    }

    override suspend fun updateHeroFavoriteState(hero: Hero) {
        heroDao.updateHerosAsFavorites(hero)
    }

}