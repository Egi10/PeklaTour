package id.co.egifcb.peklatour.peklatour

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import com.google.gson.Gson
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.Order
import id.co.egifcb.peklatour.peklatour.data.repository.tour.model.TourList
import id.co.egifcb.peklatour.peklatour.navigation.NavigationItem
import id.co.egifcb.peklatour.peklatour.navigation.Screen
import id.co.egifcb.peklatour.peklatour.ui.auth.login.LoginRoute
import id.co.egifcb.peklatour.peklatour.ui.auth.register.RegisterRoute
import id.co.egifcb.peklatour.peklatour.ui.home.HomeRoute
import id.co.egifcb.peklatour.peklatour.ui.listtour.ListTourRoute
import id.co.egifcb.peklatour.peklatour.ui.listtour.details.DetailsRoute
import id.co.egifcb.peklatour.peklatour.ui.order.OrderRoute
import id.co.egifcb.peklatour.peklatour.ui.profile.ProfileRoute
import id.co.egifcb.peklatour.peklatour.ui.splashscreen.SplashRoute
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme
import id.co.egifcb.peklatour.peklatour.ui.theme.black60
import id.co.egifcb.peklatour.peklatour.ui.ticket.TicketRoute

@Composable
fun PeklaTourApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val currentRoute = currentRoute(navHostController)

    Scaffold(
        topBar = {
            if (routeBottomBar.contains(currentRoute)) {
                TopBar(
                    navHostController = navHostController
                )
            } else if (currentRoute != Screen.Splash.route) {
                val context = LocalContext.current

                TopBarContent(
                    title = titleTopAppBar(
                        route = currentRoute.toString(),
                        context = context
                    ),
                    navigationOnClick = {
                        navHostController.navigateUp()
                    }
                )
            }
        },
        bottomBar = {
            if (routeBottomBar.contains(currentRoute)) {
                BottomBar(
                    navHostController = navHostController
                )
            }
        },
        modifier = modifier,
        content = {
            NavHost(
                navController = navHostController,
                startDestination = Screen.Splash.route,
                modifier = Modifier
                    .padding(it)
            ) {
                composable(
                    route = Screen.Splash.route
                ) {
                    SplashRoute(
                        navigationToHome = {
                            navHostController.popBackStack()
                            navHostController.navigate(Screen.Home.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                // Dashboard
                composable(
                    route = Screen.Home.route
                ) {
                    HomeRoute(
                        onTourTypeClick = { tour ->
                            navHostController.navigate(
                                "listTour/${tour.typeOfTravel}"
                            )
                        }
                    )
                }

                composable(
                    route = Screen.Order.route
                ) {
                    OrderRoute(
                        detailOnClick = { order ->
                            val json = Uri.encode(Gson().toJson(order))
                            navHostController.navigate(
                                "detail/$json"
                            )
                        }
                    )
                }

                composable(
                    route = Screen.Profile.route
                ) {
                    ProfileRoute(
                        loginOnClick = {
                            navHostController.navigate(
                                Screen.Login.route
                            ) {
                                launchSingleTop = true
                            }
                        },
                        registerOnClick = {
                            navHostController.navigate(
                                Screen.Register.route
                            ) {
                                launchSingleTop = true
                            }
                        },
                        logoutOnClick = {
                            navHostController.popBackStack()
                            navHostController.navigate(
                                Screen.Home.route
                            ) {
                                popUpTo(Screen.Profile.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                // Auth
                composable(
                    route = Screen.Login.route
                ) {
                    LoginRoute(
                        onSuccessLogin = {
                            navHostController.popBackStack()
                            navHostController.navigate(
                                Screen.Home.route
                            ) {
                                popUpTo(Screen.Profile.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                composable(
                    route = Screen.Register.route
                ) {
                    RegisterRoute(
                        onSuccessRegister = {
                            navHostController.popBackStack()
                            navHostController.navigate(
                                Screen.Home.route
                            ) {
                                popUpTo(Screen.Profile.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                // Detail Order
                composable(
                    route = Screen.DetailOrder.route,
                    arguments = listOf(
                        navArgument("order") {
                            type = NavType.StringType
                        }
                    )
                ) { backStack ->
                    val value = backStack.arguments?.getString("order")
                    val order = Gson().fromJson(value, Order::class.java)

                    TicketRoute(
                        order = order
                    )
                }

                // ListTour
                composable(
                    route = Screen.ListTour.route,
                    arguments = listOf(
                        navArgument("type") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) { backStack ->
                    val value = backStack.arguments?.getString("type") ?: ""

                    ListTourRoute(
                        tourType = value,
                        onItemClick = { tourList ->
                            val json = Uri.encode(Gson().toJson(tourList))
                            navHostController.navigate(
                                "detailListTour/$json"
                            )
                        }
                    )
                }

                // Detail ListTour
                composable(
                    route = Screen.DetailsListTour.route,
                    arguments = listOf(
                        navArgument("data") {
                            type = NavType.StringType
                        }
                    )
                ) { backStack ->
                    val value = backStack.arguments?.getString("data")
                    val tourList = Gson().fromJson(value, TourList::class.java)

                    DetailsRoute(
                        tourList = tourList
                    )
                }
            }
        }
    )
}

private val routeBottomBar = listOf(
    Screen.Home.route,
    Screen.Order.route,
    Screen.Profile.route
)

private fun titleTopAppBar(context: Context, route: String) = when (route) {
    Screen.Login.route -> context.getString(R.string.login_pekla_tour)
    Screen.DetailOrder.route -> context.getString(R.string.ticket_tour)
    Screen.ListTour.route -> context.getString(R.string.list_tour)
    Screen.DetailsListTour.route -> context.getString(R.string.detail_tour)
    else -> "Belum Ada"
}

@Composable
fun TopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.White
    ) {
        val title = when (currentRoute(navHostController)) {
            Screen.Home.route -> {
                stringResource(id = R.string.app_name)
            }

            Screen.Order.route -> {
                stringResource(id = R.string.pesanan)
            }

            else -> {
                stringResource(id = R.string.akun_saya)
            }
        }

        Text(
            text = title.uppercase(),
            style = PeklaTourTheme.typography.title,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun TopBarContent(
    title: String,
    navigationOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = title,
                style = PeklaTourTheme.typography.title,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = navigationOnClick) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
private fun BottomBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.awal),
                icon = painterResource(id = R.drawable.ic_home),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.pesanan),
                icon = painterResource(id = R.drawable.ic_checklist),
                screen = Screen.Order
            ),
            NavigationItem(
                title = stringResource(id = R.string.akun_saya),
                icon = painterResource(id = R.drawable.ic_account),
                screen = Screen.Profile
            )
        )

        BottomNavigation(
            backgroundColor = Color.White
        ) {
            val currentRoute = currentRoute(navHostController)

            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = PeklaTourTheme.typography.caption,
                            fontSize = 11.sp
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        // Back First
                        navHostController.navigate(item.screen.route) {
                            popUpTo(navHostController.graph[Screen.Home.route].id) {
                                saveState = false
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = black60
                )
            }
        }
    }
}

@Composable
private fun currentRoute(navHostController: NavHostController): String? {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}