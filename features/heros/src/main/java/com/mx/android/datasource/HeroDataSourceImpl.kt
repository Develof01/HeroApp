package com.mx.android.datasource

import com.mx.android.data.mapper.callNewRequest
import com.mx.android.data.datasource.network.IHeroDataSource
import com.mx.android.network.HeroAppService
import javax.inject.Inject

class HeroDataSourceImpl @Inject constructor(
    private val apiService: HeroAppService
): IHeroDataSource {

    override suspend fun getHeroList() = callNewRequest {
        apiService.getCharacters()
    }

}