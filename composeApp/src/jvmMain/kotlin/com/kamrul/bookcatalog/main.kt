package com.kamrul.bookcatalog

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Book Catalog CMP",
    ) {
        App()
    }
}