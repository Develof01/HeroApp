package com.mx.android.datasource

import com.mx.android.common.extensionFunctions.toResultFlow
import com.mx.android.data.datasource.network.IHeroDataSource
import com.mx.android.network.HeroAppService
import javax.inject.Inject

class HeroDataSourceImpl @Inject constructor(
    private val apiService: HeroAppService
): IHeroDataSource {

    override suspend fun getHeroList() = toResultFlow {
        apiService.getCharacters()
    }

}