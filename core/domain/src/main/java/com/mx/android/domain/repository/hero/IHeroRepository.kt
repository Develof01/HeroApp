package com.mx.android.domain.repository.hero

import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.dto.response.BaseResponse
import com.mx.android.domain.dto.response.hero.HeroResponse
import kotlinx.coroutines.flow.Flow

interface IHeroRepository {
    suspend fun getHeroList(): Flow<NetworkResult<BaseResponse<HeroResponse>?>>
}