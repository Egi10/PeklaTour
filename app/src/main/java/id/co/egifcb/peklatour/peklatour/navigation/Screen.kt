package id.co.egifcb.peklatour.peklatour.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Order : Screen("order")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")

    object DetailOrder : Screen("detail/{order}")

    object ListTour: Screen("listTour/{type}")
    object DetailsListTour: Screen("detailListTour/{data}")

    object DeveloperProfile: Screen("developer")
}
