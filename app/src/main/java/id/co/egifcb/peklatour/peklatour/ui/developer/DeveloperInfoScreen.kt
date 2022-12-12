package id.co.egifcb.peklatour.peklatour.ui.developer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.ui.theme.PeklaTourTheme

@Composable
fun DeveloperInfoScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                all = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = "https://media-exp1.licdn.com/dms/image/C5103AQHDlOW-Aes34w/profile-displayphoto-shrink_800_800/0/1541402590633?e=1676505600&v=beta&t=KciUOrGjsZHkJSL9sJpHVMFIDnkACfU36OqxR5UrG5w",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.no_image),
            error = painterResource(id = R.drawable.no_image),
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
        )
        
        Text(
            text = "Julsapargi Nursam",
            style = PeklaTourTheme.typography.title,
            fontSize = 16.sp
        )

        Text(
            text = "egifcb@gmail.com",
            style = PeklaTourTheme.typography.caption
        )
    }
}