package com.mx.android.domain.useCases.hero

import com.mx.android.data.mappers.hero.asDomain
import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.modules.Hero
import com.mx.android.domain.repository.hero.IHeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetHeroListUseCase @Inject constructor(
    private val repository: IHeroRepository
) {

    suspend operator fun invoke(): Flow<StateGetHeroListUseCase<List<Hero>>> = flow {
        repository.getHeroList()
            .catch { e ->
                emit(StateGetHeroListUseCase.Error(e.message ?: ""))
            }
            .collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            emit(StateGetHeroListUseCase.Success(it.apiData.results.asDomain()))
                        }
                    }
                    is NetworkResult.Error -> {
                        emit(StateGetHeroListUseCase.Error(response.error?.message ?: ""))
                    }
                    is NetworkResult.Loading -> {
                        emit(StateGetHeroListUseCase.Loading(response.isLoading))
                    }
                }
            }
    }


}