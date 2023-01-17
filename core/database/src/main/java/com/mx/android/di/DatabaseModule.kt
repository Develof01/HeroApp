package com.mx.android.di

import android.app.Application
import com.mx.android.database.AppDatabase
import com.mx.android.database.dao.HeroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): AppDatabase = AppDatabase.build(app)

    @Provides
    @Singleton
    fun heroDao(appDatabase: AppDatabase): HeroDao = appDatabase.heroDao()

}