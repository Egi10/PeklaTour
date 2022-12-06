package id.co.egifcb.peklatour.peklatour.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

private val colorPalette = lightColors(
    primary = cyan,
    primaryVariant = greenishCyan,
)

@Composable
fun PeklaTourTheme(
    content: @Composable () -> Unit
) {
    val peklaTourTypography = PeklaTourTypography(
        body = TextStyle(
            fontFamily = ubuntuFamily,
            fontWeight = FontWeight.Medium
        ),
        title = TextStyle(
            fontFamily = ubuntuFamily,
            fontWeight = FontWeight.Bold
        ),
        caption = TextStyle(
            fontFamily = ubuntuFamily,
            fontWeight = FontWeight.Normal
        )
    )

    CompositionLocalProvider(
        LocalPeklaTourTypography provides peklaTourTypography
    ) {
        MaterialTheme(
            colors = colorPalette,
            content = content
        )
    }
}

object PeklaTourTheme {
    val typography: PeklaTourTypography
        @Composable
        get() = LocalPeklaTourTypography.current
}