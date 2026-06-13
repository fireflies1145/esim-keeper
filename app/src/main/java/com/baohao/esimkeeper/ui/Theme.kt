package com.baohao.esimkeeper.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val KeeperBlue = Color(0xFF1296F3)
val KeeperGreen = Color(0xFF35B86B)
val KeeperRed = Color(0xFFE5484D)
val KeeperBackground = Color(0xFFF7F7FB)
val KeeperDarkBackground = Color(0xFF111318)
val KeeperCard = Color(0xFFFFFFFF)
val KeeperDarkCard = Color(0xFF1D2028)
val KeeperMuted = Color(0xFF7D7D86)

private val KeeperLightColorScheme = lightColorScheme(
    primary = KeeperBlue,
    secondary = KeeperGreen,
    error = KeeperRed,
    background = KeeperBackground,
    surface = KeeperCard,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onError = Color.White,
    onBackground = Color(0xFF17171C),
    onSurface = Color(0xFF17171C),
)

private val KeeperDarkColorScheme = darkColorScheme(
    primary = Color(0xFF58B7FF),
    secondary = Color(0xFF5CD489),
    error = Color(0xFFFF6B70),
    background = KeeperDarkBackground,
    surface = KeeperDarkCard,
    onPrimary = Color(0xFF06233A),
    onSecondary = Color(0xFF062514),
    onError = Color(0xFF3A0A0D),
    onBackground = Color(0xFFF2F5FA),
    onSurface = Color(0xFFF2F5FA),
    surfaceVariant = Color(0xFF2A2E39),
    onSurfaceVariant = Color(0xFFC8CEDA),
)

@Composable
fun ESimKeeperTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) KeeperDarkColorScheme else KeeperLightColorScheme,
        typography = MaterialTheme.typography,
        content = content,
    )
}
