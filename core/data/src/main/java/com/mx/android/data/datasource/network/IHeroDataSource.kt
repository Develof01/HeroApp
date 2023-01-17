package com.mx.android.data.datasource.network

import com.mx.android.domain.modules.network.NetworkResult
import com.mx.android.domain.modules.network.BaseResponse
import com.mx.android.network.dto.response.hero.HeroResponse
import kotlinx.coroutines.flow.Flow

interface IHeroDataSource {
    suspend fun getHeroList(): Flow<NetworkResult<BaseResponse<HeroResponse>?>>
}