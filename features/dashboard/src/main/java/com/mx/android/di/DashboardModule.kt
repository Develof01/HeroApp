package com.mx.android.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mx.android.common.extensionFunctions.dataStore
import com.mx.android.data.datasource.local.ISettingsLocalDataSource
import com.mx.android.data.repository.AppSettingRepositoryImpl
import com.mx.android.datasource.AppSettingsDataSourceImpl
import com.mx.android.domain.repository.setting.IAppSettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DashboardModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context):
            DataStore<Preferences> = appContext.dataStore

    @Provides
    fun bindLocalSettingDataSource(
        @ApplicationContext appContext: Context,
        dataStore: DataStore<Preferences>
    ): ISettingsLocalDataSource =
        AppSettingsDataSourceImpl(appContext, dataStore)

    @Provides
    fun bindSettingRepository(
        localISettingsLocalDataSource: ISettingsLocalDataSource
    ): IAppSettingRepository =
        AppSettingRepositoryImpl(localISettingsLocalDataSource)

}