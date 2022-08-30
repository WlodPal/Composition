package com.vladimir.composition.domain.usecases

import com.vladimir.composition.domain.entity.GameSettings
import com.vladimir.composition.domain.entity.Level
import com.vladimir.composition.domain.repository.GameRepository

class GetGameSettingsUseCase (
    private val repository: GameRepository
        ) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}