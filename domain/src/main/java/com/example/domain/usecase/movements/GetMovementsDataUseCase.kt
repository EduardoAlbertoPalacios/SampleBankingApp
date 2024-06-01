package com.example.domain.usecase.movements

import com.example.domain.entities.MovementsEntity
import com.example.domain.repository.MovementsRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovementsDataUseCase @Inject constructor(private val repository: MovementsRepository) :
    UseCase<Any, List<MovementsEntity>>() {
    override suspend fun buildUseCase(params: Any): Flow<List<MovementsEntity>> =
        repository.getAllMovements()
}
