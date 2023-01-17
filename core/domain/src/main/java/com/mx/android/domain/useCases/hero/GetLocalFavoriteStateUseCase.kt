package com.mx.android.domain.useCases.hero

import com.mx.android.domain.repository.hero.IHeroRepository
import javax.inject.Inject

class GetLocalFavoriteStateUseCase @Inject constructor(
    private val repository: IHeroRepository
) {

    suspend operator fun invoke(id: Int) = repository.getLocalFavoriteState(id)

}