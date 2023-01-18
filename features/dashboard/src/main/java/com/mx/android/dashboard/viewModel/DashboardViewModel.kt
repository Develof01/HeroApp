package com.mx.android.dashboard.viewModel

import android.os.Bundle
import com.mx.android.common.base.scope.ScopedViewModel
import com.mx.android.domain.useCases.settings.ChangeThemeModeUseCase
import com.mx.android.domain.useCases.settings.GetLocalSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getSettings: GetLocalSettingsUseCase,
    private val changeThemeModeUseCase: ChangeThemeModeUseCase
): ScopedViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme = _isDarkTheme.asStateFlow()

    override fun setUp(bundle: Bundle?) {
        launch {
            getSettings.invoke().collect { settings ->
                _isDarkTheme.value = settings.darkModeTheme
            }
        }
    }

    fun changeThemeMode() {
        launch {
            changeThemeModeUseCase.invoke(!isDarkTheme.value)
        }
    }

}