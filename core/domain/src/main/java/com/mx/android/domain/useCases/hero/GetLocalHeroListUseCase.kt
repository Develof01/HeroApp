package com.mx.android.domain.useCases.hero

import com.mx.android.domain.modules.network.NetworkResult
import com.mx.android.domain.repository.hero.IHeroRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalHeroListUseCase @Inject constructor(private val repository: IHeroRepository) {

    suspend operator fun invoke() = flow {
        repository.getLocalHeroList()
            .catch { e ->
                emit(NetworkResult.Error(e.hashCode(), e.message ?: ""))
            }
            . collect { heros ->
                emit(heros)
            }
    }
}