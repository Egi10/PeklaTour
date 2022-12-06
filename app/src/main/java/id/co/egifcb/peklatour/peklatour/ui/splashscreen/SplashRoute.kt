package id.co.egifcb.peklatour.peklatour.ui.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    navigationToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    }
                )
            )

            delay(2_000)

            navigationToHome.invoke()
        }
    )

    SplashScreen(
        modifier = modifier
            .scale(
                scale = scale.value
            )
    )
}