// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.*
import navigation.Navigation
import navigation.Screen
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        state = WindowState(
            placement = WindowPlacement.Maximized, position = WindowPosition(Alignment.Center)
        )
    ) {
        val darkMode = isSystemInDarkTheme()
        MaterialTheme(colors = if (darkMode) darkColors() else lightColors()) {
            Surface(color = MaterialTheme.colors.background) {
                Navigation(Screen.LOGIN)
            }
        }
    }
}
