package id.co.egifcb.peklatour.peklatour.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourError
import id.co.egifcb.peklatour.peklatour.ui.component.PeklaTourLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value.loading) {
        PeklaTourLoading()
    }

    if (uiState.value.isSuccess) {
        HomeScreen(
            promo = uiState.value.promo,
            destinationFavorite = uiState.value.destinationFavorite,
            tourType = uiState.value.tourType
        )
    }

    if (uiState.value.isError) {
        PeklaTourError(
            message = uiState.value.error,
            onClick = {
                viewModel.getHome()
            }
        )
    }
}