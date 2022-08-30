package com.vladimir.composition.domain.usecases

import com.vladimir.composition.domain.entity.Question
import com.vladimir.composition.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {

    // invoke чтобы юзкейс можна передавать как метод
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}