package com.mx.android.network

import com.mx.android.domain.modules.network.BaseResponse
import com.mx.android.network.dto.response.hero.HeroResponse
import retrofit2.Response
import retrofit2.http.GET

interface HeroAppService {

    @GET("characters")
    suspend fun getCharacters(): Response<BaseResponse<HeroResponse>?>

}