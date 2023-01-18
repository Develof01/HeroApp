package com.mx.android.domain.useCases.settings

import com.mx.android.domain.repository.setting.IAppSettingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalSettingsUseCase @Inject constructor(
    private val repository: IAppSettingRepository
) {
    suspend operator fun invoke() = flow {
        repository.getSettings()
            . collect { settings ->
                emit(settings)
            }
    }
}