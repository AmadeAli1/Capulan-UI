package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import screens.LoginScreen
import screens.MenuScreen

@Composable
fun Navigation(initScreen: Screen) {
    val navigate = remember { mutableStateOf(initScreen) }

    when (navigate.value) {
        Screen.LOGIN -> LoginScreen(navigate = navigate)
        Screen.MENU -> MenuScreen()
    }

}