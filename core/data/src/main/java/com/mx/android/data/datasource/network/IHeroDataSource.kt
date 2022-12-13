package com.mx.android.data.datasource.network

import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.dto.response.BaseResponse
import com.mx.android.domain.dto.response.hero.HeroResponse
import kotlinx.coroutines.flow.Flow

interface IHeroDataSource {
    suspend fun getHeroList(): Flow<NetworkResult<BaseResponse<HeroResponse>?>>
}