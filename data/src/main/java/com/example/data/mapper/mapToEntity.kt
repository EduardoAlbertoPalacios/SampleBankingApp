package com.example.data.mapper

import com.example.data.model.response.MovementsResponse
import com.example.domain.entities.MovementsEntity

fun MovementsResponse.mapToEntity() = MovementsEntity(
    title,
    description,
    amount.toDouble(),
    date
)

fun List<MovementsResponse>.mapListToEntity() = this.map {
    it.mapToEntity()
}

