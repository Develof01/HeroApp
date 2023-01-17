package com.mx.android.domain.useCases.hero

import com.mx.android.domain.modules.hero.Hero
import com.mx.android.domain.repository.hero.IHeroRepository
import javax.inject.Inject

class UpdateHeroFavoriteStateUseCase @Inject constructor(
    private val repository: IHeroRepository
) {

    suspend operator fun invoke(hero: Hero) {
        repository.updateLocalHeroFavoriteState(hero)
    }
}