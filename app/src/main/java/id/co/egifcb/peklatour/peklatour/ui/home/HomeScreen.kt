package id.co.egifcb.peklatour.peklatour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.DestinationFavorite
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Promo
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourType
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourCard
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun HomeScreen(
    promo: List<Promo>,
    destinationFavorite: List<DestinationFavorite>,
    tourType: List<TourType>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement
            .spacedBy(20.dp)
    ) {
        // TODO Change to Slider
        AsyncImage(
            model = promo[0].image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.no_image),
            error = painterResource(id = R.drawable.no_image),
            modifier = Modifier
                .fillMaxWidth()
                // Aspect Ratio 1:0.6 = 1 / 0.6 = 1.66
                .aspectRatio(
                    ratio = 1.66f
                )
        )

        TextHeader(
            text = stringResource(id = R.string.pilih_tour_mu),
            content = {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    columns = GridCells
                        .Fixed(3),
                    userScrollEnabled = false,
                    contentPadding = PaddingValues(
                        start = 5.dp,
                        end = 5.dp
                    ),
                    content = {
                        items(tourType) {
                            ListItemTourType(
                                image = it.image,
                                typeOfTravel = it.typeOfTravel
                            )
                        }
                    }
                )
            }
        )

        TextHeader(
            text = stringResource(id = R.string.tour_favorite),
            content = {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        start = 5.dp,
                        end = 5.dp,
                        bottom = 5.dp
                    ),
                    content = {
                        items(destinationFavorite) {
                            ListItemDestinationFavorite(
                                image = it.image,
                                placeName = it.placeName,
                                lengthOfJourney = it.lengthOfJourney,
                                modifier = Modifier
                                    .fillParentMaxWidth(0.8f)
                                    // Aspect Ratio 1:0.6 = 1 / 0.6 = 1.66
                                    .aspectRatio(
                                        ratio = 1.66f
                                    )
                            )
                        }
                    }
                )
            }
        )
    }
}

@Composable
fun TextHeader(
    text: String,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            style = PeklaTourTheme.typography.title,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 5.dp
                )
        )

        content.invoke(this)
    }
}

@Composable
private fun ListItemTourType(
    image: String,
    typeOfTravel: String,
    modifier: Modifier = Modifier
) {
    PeklaTourCard(
        modifier = modifier
            .padding(
                all = 5.dp
            )
            .size(100.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = image,
                contentDescription = typeOfTravel,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.no_image),
                error = painterResource(id = R.drawable.no_image),
            )

            Text(
                text = typeOfTravel.uppercase(),
                style = PeklaTourTheme.typography.body,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ListItemDestinationFavorite(
    image: String,
    placeName: String,
    lengthOfJourney: String,
    modifier: Modifier = Modifier
) {
    PeklaTourCard(
        modifier = modifier
            .padding(
                all = 5.dp
            )
    ) {
        Box(
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = image,
                contentDescription = placeName,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.no_image),
                error = painterResource(id = R.drawable.no_image),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        all = 10.dp
                    )
            ) {
                Text(
                    text = placeName,
                    style = PeklaTourTheme.typography.body,
                    fontSize = 16.sp,
                    color = Color.White
                )

                Text(
                    text = lengthOfJourney,
                    style = PeklaTourTheme.typography.caption,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}