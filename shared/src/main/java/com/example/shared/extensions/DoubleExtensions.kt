package com.example.shared.extensions

import java.text.NumberFormat

fun Double.formatNumber(): String = NumberFormat.getCurrencyInstance().format(this)
