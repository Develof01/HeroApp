package com.mx.android.data.repository

import com.mx.android.data.datasource.local.ISettingsLocalDataSource
import com.mx.android.domain.repository.setting.IAppSettingRepository
import javax.inject.Inject

class AppSettingRepositoryImpl @Inject constructor(
    private val localDataSource: ISettingsLocalDataSource
): IAppSettingRepository {

    override suspend fun getSettings() = localDataSource.getAppSettings()

    override suspend fun changeDarkMode(isDarkMode: Boolean) {
        localDataSource.changeDarkModeValue(isDarkMode)
    }

}