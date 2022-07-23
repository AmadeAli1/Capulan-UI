package screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import kotlinx.coroutines.delay
import navigation.Screen
import java.io.File

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(navigate: MutableState<Screen>) {

    LaunchedEffect(key1 = Unit) {
        delay(timeMillis = 1500L)
        navigate.value = Screen.LOGIN
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = true,
            enter = scaleIn(animationSpec = tween(easing = FastOutSlowInEasing))
        ) {
            Image(
                bitmap = loadImageBitmap(inputStream = File("src/jvmMain/resources/screen.png").inputStream()),
                null,
                modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
            )
        }
    }

}