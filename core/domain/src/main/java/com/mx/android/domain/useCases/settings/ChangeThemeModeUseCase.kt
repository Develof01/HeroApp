package com.mx.android.domain.useCases.settings

import com.mx.android.domain.repository.setting.IAppSettingRepository
import javax.inject.Inject

class ChangeThemeModeUseCase @Inject constructor(
    private val repository: IAppSettingRepository
) {

    suspend operator fun invoke(isDarkMode: Boolean) = repository.changeDarkMode(isDarkMode)

}