package com.example.storidemoapp.mocks

import com.example.domain.entities.MovementsEntity

private val firstMovement = MovementsEntity(
    "Wal-Mart",
    "Bought groceries",
    -324.00,
    "11/05/24",
)
private val secondMovement = MovementsEntity(
    "paid services",
    "telephony",
    -224.00,
    "23/05/24",
)
private val thirdMovement = MovementsEntity(
    "Transfer",
    "Third party transfer bank",
    231.00,
    "07/05/24",
)

fun getMockMovements() = listOf(
    firstMovement, secondMovement, thirdMovement
)
