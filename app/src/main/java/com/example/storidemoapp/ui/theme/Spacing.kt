package com.example.storidemoapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val dimen1: Dp = 1.dp,
    val dimen4: Dp = 4.dp,
    val dimen6: Dp = 6.dp,
    val dimen8: Dp = 8.dp,
    val dimen12: Dp = 12.dp,
    val dimen16: Dp = 16.dp,
    val dimen24: Dp = 24.dp,
    val dimen32: Dp = 32.dp,
    val dimen48: Dp = 48.dp,
    val dimen60: Dp = 60.dp,
    val dimen64: Dp = 64.dp,
    val dimen120: Dp = 120.dp,
    val dimen150: Dp = 150.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current