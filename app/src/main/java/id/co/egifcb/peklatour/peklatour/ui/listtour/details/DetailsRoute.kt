package id.co.egifcb.peklatour.peklatour.ui.listtour.details

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourList

@Composable
fun DetailsRoute(
    tourList: TourList
) {
    val context = LocalContext.current

    DetailsScreen(
        tourList = tourList,
        onOrderTour = {
            Toast.makeText(context, "Mohon maaf, kami lagi melakukan pemiliharaan system", Toast.LENGTH_SHORT).show()
        }
    )
}