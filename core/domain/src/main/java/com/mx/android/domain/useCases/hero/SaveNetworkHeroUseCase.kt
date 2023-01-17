package com.mx.android.domain.useCases.hero

import com.mx.android.domain.repository.hero.IHeroRepository
import com.mx.android.domain.modules.network.NetworkResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SaveNetworkHeroUseCase @Inject constructor(private val repository: IHeroRepository) {

    suspend operator fun invoke() =
        flow {
            repository.getHeroList()
                .catch { e ->
                    emit(NetworkResult.Error(e.hashCode(), e.message ?: ""))
                }
                .onEach { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            response.data?.let { heros ->
                                repository.insertLocalHeroList(heros)
                            }
                        }
                        is NetworkResult.Error -> println(response.error?.message ?: "")
                    }
                }
                .collect { response ->
                    emit(response)
                }
        }

}