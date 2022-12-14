package com.mx.android.heros.viewModel

import android.util.Log
import com.mx.android.common.base.scope.ScopedViewModel
import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.useCases.hero.GetHeroListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val getHeroListUseCase: GetHeroListUseCase
) : ScopedViewModel() {

    override fun onInit() {
        launch {
            getHeroListUseCase.invoke()
                .onStart {
                    Log.v("OMI", "LOADING")
                }
                .catch {
                    Log.v("OMI", "CATCH ERROR")
                }
                .collect { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            Log.v("OMI", response.data.toString())
                        }
                        is NetworkResult.Error -> {
                            Log.v("OMI", "ERROR")
                        }
                    }
                }

        }
    }

}