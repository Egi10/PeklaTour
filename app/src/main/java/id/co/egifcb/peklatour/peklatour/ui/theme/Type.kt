package id.co.egifcb.peklatour.peklatour.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import id.co.egifcb.peklatour.peklatour.R

@Immutable
data class PeklaTourTypography(
    val caption: TextStyle,
    val body: TextStyle,
    val title: TextStyle
)

val LocalPeklaTourTypography = staticCompositionLocalOf {
    PeklaTourTypography(
        caption = TextStyle.Default,
        body = TextStyle.Default,
        title = TextStyle.Default
    )
}

val ubuntuFamily = FontFamily(
    Font(R.font.ubuntu_regular, FontWeight.Normal),
    Font(R.font.ubuntu_medium, FontWeight.Medium),
    Font(R.font.ubuntu_bold, FontWeight.Bold)
)