// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import screens.LoginScreen
import theme.blueTheme

@Composable
fun App() {
    MaterialTheme(
        colors = blueTheme
    ) {
        Surface(color = MaterialTheme.colors.background){ LoginScreen() }
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, resizable = false, state = WindowState(placement = WindowPlacement.Maximized)) {
        App()
    }
}
