package com.mx.android.domain.repository.hero

import com.mx.android.domain.dto.response.BaseResponse
import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.dto.response.hero.HeroResponse
import com.mx.android.domain.modules.Hero
import kotlinx.coroutines.flow.Flow

interface IHeroRepository {
    suspend fun getHeroList():  Flow<NetworkResult<BaseResponse<HeroResponse>?>>
}