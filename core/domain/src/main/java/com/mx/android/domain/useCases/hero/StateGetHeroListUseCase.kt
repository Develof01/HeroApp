package com.mx.android.domain.useCases.hero

sealed class StateGetHeroListUseCase<out T> {
    data class Success<out T>(val data: T): StateGetHeroListUseCase<T>()
    data class Loading(val isLoading: Boolean): StateGetHeroListUseCase<Nothing>()
    data class Error(val message: String): StateGetHeroListUseCase<Nothing>()
}