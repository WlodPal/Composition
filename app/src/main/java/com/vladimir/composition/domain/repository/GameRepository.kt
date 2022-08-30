package com.vladimir.composition.domain.repository

import com.vladimir.composition.domain.entity.GameSettings
import com.vladimir.composition.domain.entity.Level
import com.vladimir.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}