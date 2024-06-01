package com.example.data.model.response

data class MovementsResponse(
    val amount: String,
    val date: String,
    val description: String,
    val title: String
) {
    constructor() : this(
        "0.00",
        "",
        "",
        ""
    )
}
