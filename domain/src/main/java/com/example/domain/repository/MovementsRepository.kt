package com.example.domain.repository

import com.example.domain.entities.MovementsEntity
import kotlinx.coroutines.flow.Flow

interface MovementsRepository {
    suspend fun getAllMovements(): Flow<List<MovementsEntity>>
}