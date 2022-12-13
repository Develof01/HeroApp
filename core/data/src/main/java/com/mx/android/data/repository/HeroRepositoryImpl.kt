package com.mx.android.data.repository

import com.mx.android.data.datasource.network.IHeroDataSource
import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.dto.response.BaseResponse
import com.mx.android.domain.dto.response.hero.HeroResponse
import com.mx.android.domain.repository.hero.IHeroRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val networkIHeroDataSource: IHeroDataSource
): IHeroRepository {

    override suspend fun getHeroList(): Flow<NetworkResult<BaseResponse<HeroResponse>?>> {
       return networkIHeroDataSource.getHeroList()
    }

}