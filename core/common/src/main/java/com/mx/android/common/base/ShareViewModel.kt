package com.mx.android.common.base

import android.content.res.Configuration
import android.os.Bundle
import com.mx.android.common.base.scope.ScopedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShareViewModel: ScopedViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme = _isDarkTheme.asStateFlow()

    override fun setUp(bundle: Bundle?) {
        bundle?.let {
            when(it.getInt("darkMode") and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> _isDarkTheme.value = true
                Configuration.UI_MODE_NIGHT_NO -> _isDarkTheme.value = false
                Configuration.UI_MODE_NIGHT_UNDEFINED -> _isDarkTheme.value = true
            }
        }
    }

    fun changeThemeMode() {
        _isDarkTheme.value = !isDarkTheme.value
    }

}