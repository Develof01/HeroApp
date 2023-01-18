package com.mx.android.data.datasource.local

import com.mx.android.domain.modules.settings.Settings
import kotlinx.coroutines.flow.Flow

interface ISettingsLocalDataSource {

    suspend fun getAppSettings(): Flow<Settings>
    suspend fun changeDarkModeValue(isDarkMode: Boolean)

}