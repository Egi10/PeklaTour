package id.co.egifcb.peklatour.peklatour.ui.listtour

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourList
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourCard
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun ListTourScreen(
    listTour: List<TourList>,
    onItemClick: (TourList) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            all = 5.dp
        ),
        content = {
            items(
                listTour,
                key = {
                    it.no
                }
            ) {
                ListTourItem(
                    image = it.image,
                    placeName = it.placeName,
                    onClick = {
                        onItemClick.invoke(it)
                    }
                )
            }
        }
    )
}

@Composable
private fun ListTourItem(
    image: String,
    placeName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PeklaTourCard(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(
                all = 5.dp
            )
            .clickable {
                onClick.invoke()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
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

            Text(
                text = placeName,
                style = PeklaTourTheme.typography.body,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        all = 10.dp
                    ),
                color = Color.White
            )
        }
    }
}