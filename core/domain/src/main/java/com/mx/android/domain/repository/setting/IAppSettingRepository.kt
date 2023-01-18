package com.mx.android.domain.repository.setting

import com.mx.android.domain.modules.settings.Settings
import kotlinx.coroutines.flow.Flow

interface IAppSettingRepository {

    suspend fun getSettings(): Flow<Settings>
    suspend fun changeDarkMode(isDarkMode: Boolean)

}