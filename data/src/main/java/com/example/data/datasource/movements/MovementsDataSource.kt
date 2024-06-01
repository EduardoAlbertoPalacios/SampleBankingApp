package com.example.data.datasource.movements

import com.example.data.model.response.MovementsResponse
import kotlinx.coroutines.flow.Flow

interface MovementsDataSource {
    suspend fun getAllMovements(): Flow<List<MovementsResponse>>
}