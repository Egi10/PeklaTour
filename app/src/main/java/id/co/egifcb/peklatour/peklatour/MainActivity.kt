package id.co.egifcb.peklatour.peklatour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PeklaTourTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentColor = MaterialTheme.colors.background,
                    contentWindowInsets = WindowInsets(
                        0, 0, 0, 0
                    ),
                    content = { paddingValues ->
                        PeklaTourApp(
                            modifier = Modifier
                                .padding(
                                    paddingValues = paddingValues
                                )
                                .statusBarsPadding()
                                .navigationBarsPadding(),
                        )
                    }
                )
            }
        }
    }
}