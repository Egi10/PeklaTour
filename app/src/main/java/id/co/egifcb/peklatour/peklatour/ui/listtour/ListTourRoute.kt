package id.co.egifcb.peklatour.peklatour.ui.listtour

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourList
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourEmpty
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourError
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListTourRoute(
    tourType: String,
    onItemClick: (TourList) -> Unit,
    viewModel: ListTourViewModel = koinViewModel()
) {
    LaunchedEffect(
        key1 = tourType,
        block = {
            viewModel.getListTour(tourType = tourType)
        }
    )

    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value.isLoading) {
        PeklaTourLoading()
    }

    if (uiState.value.isSuccess) {
        ListTourScreen(
            listTour = uiState.value.listTour,
            onItemClick = onItemClick
        )
    }

    if (uiState.value.isError) {
        PeklaTourError(
            message = uiState.value.messageError,
            onClick = {
                viewModel.getListTour(tourType = tourType)
            }
        )
    }

    if (uiState.value.isEmpty) {
        PeklaTourEmpty(
            title = stringResource(R.string.message_empty_list_tour, tourType)
        )
    }
}