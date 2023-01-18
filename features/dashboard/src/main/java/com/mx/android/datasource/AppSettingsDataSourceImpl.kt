package com.mx.android.datasource

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.mx.android.data.datasource.local.ISettingsLocalDataSource
import com.mx.android.domain.modules.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppSettingsDataSourceImpl @Inject constructor(
    private val context: Context,
    private val dataStore: DataStore<Preferences>,
): ISettingsLocalDataSource {

    companion object {
        const val SETTING_KEY = "settings_key"
    }

    override suspend fun getAppSettings(): Flow<Settings> {
        val systemDarkModeConfig =
            when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> true
                Configuration.UI_MODE_NIGHT_NO -> false
                Configuration.UI_MODE_NIGHT_UNDEFINED -> true
                else -> true
        }
        val darkModePreference = booleanPreferencesKey(SETTING_KEY)
        return dataStore.data.map { preferences ->
            Settings(darkModeTheme = preferences[darkModePreference] ?: systemDarkModeConfig)
        }
    }

    override suspend fun changeDarkModeValue(isDarkMode: Boolean) {
        val darkModePreference = booleanPreferencesKey(SETTING_KEY)
        dataStore.edit { preferences ->
            preferences[darkModePreference] = isDarkMode
        }
    }

}