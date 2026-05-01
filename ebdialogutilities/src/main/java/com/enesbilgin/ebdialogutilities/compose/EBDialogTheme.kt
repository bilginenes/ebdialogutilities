/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// EB Specific Colors
val EBRed = Color(0xFFD32F2F)
val EBRedDark = Color(0xFFB71C1C)
val EBBlue = Color(0xFF0277BD)
val EBGreen = Color(0xFF43A047)

private val DarkColorScheme = darkColorScheme(
    primary = EBRed,
    onPrimary = Color.White,
    primaryContainer = EBRedDark,
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF424242),
    onSecondary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF333333),
    onSurfaceVariant = Color(0xFFBDBDBD)
)

private val LightColorScheme = lightColorScheme(
    primary = EBRed,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFCDD2),
    onPrimaryContainer = EBRedDark,
    secondary = Color(0xFF757575),
    onSecondary = Color.White,
    background = Color(0xFFFAFAFA),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = Color(0xFFEEEEEE),
    onSurfaceVariant = Color(0xFF424242)
)

val EBTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

@Composable
fun EBDialogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EBTypography,
        content = content
    )
}
