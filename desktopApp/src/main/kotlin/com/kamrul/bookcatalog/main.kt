package com.kamrul.bookcatalog

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import bookcatalogcmp.desktopapp.generated.resources.Res
import bookcatalogcmp.desktopapp.generated.resources.window_icon
import com.kamrul.bookcatalog.app.App
import com.kamrul.bookcatalog.di.initKoin
import org.jetbrains.compose.resources.painterResource

fun main() {
    initKoin()
    application {
        val state = rememberWindowState(
            size = DpSize(412.dp, 915.dp),
            position = WindowPosition.Aligned(Alignment.Center)
        )
        Window(
            title = "BookCatalog",
            onCloseRequest = ::exitApplication,
            state = state,
            icon = painterResource(Res.drawable.window_icon)
        ) {
            App()
        }
    }
}
