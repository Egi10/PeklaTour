package id.co.egifcb.peklatour.peklatour.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme
import id.co.egifcb.peklatour.peklatour.R

@Composable
fun PeklaTourEmpty(
    @DrawableRes imageId: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                all = 15.dp
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 15.dp
                )
        )

        Text(
            text = title,
            style = PeklaTourTheme.typography.body,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EmptyPreview() {
    PeklaTourTheme {
        PeklaTourEmpty(
            imageId = R.drawable.ic_caravan,
            title = stringResource(id = R.string.maaf_data_tour_masih_kosong)
        )
    }
}