package com.mx.android.network

import com.mx.android.domain.dto.response.BaseHeroResponse
import com.mx.android.domain.dto.response.HeroResponse
import retrofit2.Response
import retrofit2.http.GET

interface HeroAppService {

    @GET("characters")
    fun getCharacters(): Response<BaseHeroResponse<HeroResponse>?>

}