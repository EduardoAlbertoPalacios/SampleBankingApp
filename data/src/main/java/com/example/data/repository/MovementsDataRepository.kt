package com.example.data.repository

import com.example.data.datasource.movements.MovementsDataSource
import com.example.data.mapper.mapListToEntity
import com.example.domain.entities.MovementsEntity
import com.example.domain.repository.MovementsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovementsDataRepository @Inject constructor(
    private val client: MovementsDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) :
    MovementsRepository {
    override suspend fun getAllMovements(): Flow<List<MovementsEntity>> =
        client.getAllMovements().map {
            it.mapListToEntity()
        }.flowOn(coroutineDispatcher)
}