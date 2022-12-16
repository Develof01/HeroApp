package com.mx.android.heros.viewModel

import com.mx.android.common.base.scope.ScopedViewModel
import com.mx.android.domain.useCases.hero.GetHeroListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val getHeroListUseCase: GetHeroListUseCase
) : ScopedViewModel() {

    override fun onInit() {
        launch {
            getHeroListUseCase.invoke()
        }
    }
}