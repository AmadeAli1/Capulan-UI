package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import screens.LoginScreen
import screens.SplashScreen
import screens.home.*

@Composable
fun Navigation(initScreen: Screen) {
    val navigate = remember { mutableStateOf(initScreen) }

    when (navigate.value) {
        Screen.LOGIN -> LoginScreen(navigate = navigate)
        Screen.HOME -> HomeScreen(navigate = navigate)
        Screen.SAVECLIENTE -> ShowFormCliente(onBack = {
            navigate.value = it
        })
        Screen.SPLASHSCREEN -> {
            SplashScreen(navigate = navigate)
        }
        Screen.SAVEFUNCIONARIO -> ShowFormFuncionario(onBack = {
            navigate.value = it
        })
        Screen.SAVESTOCK -> {
            StockForm(onBack = { navigate.value = it })
        }
        Screen.SAVEFORNECEDOR -> {
            FornecedorForm(onBack = { navigate.value = it })
        }
        Screen.SAVEENCOMENDA ->{

        }
    }
}