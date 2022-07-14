// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import screens.LoginScreen
import screens.MenuScreen
import theme.blueTheme

@Composable
fun App() {
    MaterialTheme(
        colors = blueTheme
    ) {
//        Surface(color = MaterialTheme.colors.background){ LoginScreen() }
        Surface(color = MaterialTheme.colors.background) { MenuScreen() }
    }

}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, resizable = false, state = WindowState(
            placement = WindowPlacement.Floating, position = WindowPosition(
                Alignment.Center
            ),
            size= DpSize(950.dp,580.dp)
        )
    ) {
        App()
    }
}
