package com.mx.android.heros.viewModel

import android.os.Bundle
import com.mx.android.common.base.scope.ScopedViewModel
import com.mx.android.domain.modules.hero.Hero
import com.mx.android.domain.modules.network.NetworkResult
import com.mx.android.domain.useCases.hero.GetLocalFavoriteStateUseCase
import com.mx.android.domain.useCases.hero.GetLocalHeroListUseCase
import com.mx.android.domain.useCases.hero.SaveNetworkHeroUseCase
import com.mx.android.domain.useCases.hero.UpdateHeroFavoriteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val saveNetworkHeroUseCase: SaveNetworkHeroUseCase,
    private val getLocalHeroListUseCase: GetLocalHeroListUseCase,
    private val updateHeroFavoriteStateUseCase: UpdateHeroFavoriteStateUseCase,
) : ScopedViewModel() {

    private val _heros = MutableStateFlow(emptyList<Hero>())
    val heros = _heros.asStateFlow()

    override fun setUp(bundle: Bundle?) {
        launch {
            getLocalHeroListUseCase.invoke().collect { response ->
                when(response) {
                    is NetworkResult.Success -> response.data?.let {
                        _heros.value = it
                    }
                    is NetworkResult.Error -> {

                    }
                }
            }
        }
        launch {
            saveNetworkHeroUseCase.invoke().collect {
                println(it)
            }
        }
    }

    fun changeHeroFavoriteStatus(hero: Hero) {
        launch {
            updateHeroFavoriteStateUseCase.invoke(hero)
        }
    }
}