package com.mx.android.heros.di

import com.mx.android.data.datasource.network.IHeroDataSource
import com.mx.android.data.repository.HeroRepositoryImpl
import com.mx.android.datasource.HeroDataSourceImpl
import com.mx.android.domain.repository.hero.IHeroRepository
import com.mx.android.network.HeroAppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class HeroModule {

    @Provides
    fun bindHeroDataSource(apiService: HeroAppService): IHeroDataSource =
        HeroDataSourceImpl(apiService)

    @Provides
    fun bindHeroRepository(
        networkIHeroDataSource: IHeroDataSource
    ): IHeroRepository =
        HeroRepositoryImpl(networkIHeroDataSource)

}