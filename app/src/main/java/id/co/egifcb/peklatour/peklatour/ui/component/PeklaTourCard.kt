package id.co.egifcb.peklatour.peklatour.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PeklaTourCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 3.dp,
        shape = RoundedCornerShape(5.dp),
        content = content
    )
}