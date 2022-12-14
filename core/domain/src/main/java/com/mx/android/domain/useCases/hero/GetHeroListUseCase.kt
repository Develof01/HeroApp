package com.mx.android.domain.useCases.hero

import com.mx.android.common.extensionFunctions.logd
import com.mx.android.data.mappers.hero.asDomain
import com.mx.android.domain.dto.response.NetworkResult
import com.mx.android.domain.modules.Hero
import com.mx.android.domain.repository.hero.IHeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetHeroListUseCase @Inject constructor(private val repository: IHeroRepository) {

    suspend operator fun invoke(): Flow<NetworkResult<List<Hero>?>> = flow {
            repository.getHeroList()
                .catch { e ->
                    emit(NetworkResult.Error(e.hashCode(), e.message ?: ""))
                }
                .transform { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            response.data?.let {
                                emit(NetworkResult.Success(it.apiData.results.asDomain()))
                            }
                        }
                        else -> {
                            emit(
                                NetworkResult.Error(
                                    response.error?.code,
                                    response.error?.message ?: ""
                                )
                            )
                        }
                    }
                }
                .onEach { res ->
                    when (res) {
                        is NetworkResult.Success -> println(res.data) //todo save data in room
                        is NetworkResult.Error -> {
                            GetHeroListUseCase::class.java.simpleName.logd(res.error?.message)
                        }
                    }
                }
                .collect { response -> emit(response) }
        }
}