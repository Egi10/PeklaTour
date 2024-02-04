package id.co.egifcb.peklatour.peklatour.ui.listtour.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourList
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourButton
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme
import id.co.egifcb.peklatour.peklatour.ui.theme.grey
import id.co.egifcb.peklatour.peklatour.until.formatRupiah

@Composable
fun DetailsScreen(
    tourList: TourList,
    onOrderTour: (TourList) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(
                    top = 16.dp,
                    end = 16.dp,
                    start = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AsyncImage(
                model = tourList.image,
                contentDescription = tourList.placeName,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.no_image),
                error = painterResource(id = R.drawable.no_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(24.dp)
                    )
                    .aspectRatio(1f)
            )

            Text(
                text = tourList.placeName,
                style = PeklaTourTheme.typography.caption,
                fontSize = 18.sp
            )

            GuestsBox(
                maximumPax = tourList.maximumPax,
                minimumPax = tourList.minimumPax,
                price = tourList.price
            )

            InfoText(
                title = stringResource(id = R.string.venue_infomation),
                detail = tourList.detail
            )

            InfoText(
                title = stringResource(id = R.string.tour_duration),
                detail = tourList.durationTour
            )

            InfoText(
                title = stringResource(id = R.string.rute_perjalanan),
                detail = tourList.travelRoute
            )

            InfoText(
                title = stringResource(id = R.string.termasuk_dalam_tour),
                detail = tourList.include
            )

            InfoText(
                title = stringResource(id = R.string.tidak_termasuk_dalam_tour),
                detail = tourList.exclude
            )
        }

        PeklaTourButton(
            text = stringResource(id = R.string.book_tour),
            onClick = {
                onOrderTour.invoke(tourList)
            },
            modifier = Modifier
                .padding(
                    all = 16.dp
                ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
private fun GuestsBox(
    maximumPax: Int,
    minimumPax: Int,
    price: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(grey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                    top = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.guests),
                    style = PeklaTourTheme.typography.title
                )

                Text(
                    text = stringResource(
                        id = R.string.total_people,
                        minimumPax.toString(),
                        maximumPax.toString()
                    ),
                    style = PeklaTourTheme.typography.caption
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append("${price.toString().formatRupiah()}/")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 12.sp
                        )
                    ) {
                        append(stringResource(R.string.day))
                    }
                },
                style = PeklaTourTheme.typography.caption
            )
        }
    }
}

@Composable
fun InfoText(
    title: String,
    detail: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = PeklaTourTheme.typography.title,
            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
        )

        Text(
            text = detail.ifEmpty {
                "-"
            },
            style = PeklaTourTheme.typography.caption,
        )
    }
}


@Preview(
    showBackground = true
)
@Composable
private fun DetailsScreenPreview() {
    PeklaTourTheme {
        DetailsScreen(
            tourList = TourList(
                detail = "In publishing and graphic design, Lorem ipsum is a placeholder text " +
                        "commonly used to demonstrate the visual form of a document or a typeface " +
                        "without relying on meaningful content. Lorem ipsum may be used as a " +
                        "placeholder before final copy is available.",
                durationTour = "3 Hari",
                exclude = "Kosong",
                price = 100000,
                image = "",
                include = "Kosong",
                typePlace = "Pulau",
                maximumPax = 10,
                minimumPax = 3,
                placeName = "Lorem ipsum",
                no = 1,
                travelRoute = "Kosong"
            ),
            onOrderTour = {}
        )
    }
}