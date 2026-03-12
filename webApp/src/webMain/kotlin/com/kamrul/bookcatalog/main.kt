package com.kamrul.bookcatalog

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.kamrul.bookcatalog.di.initKoin
import kotlin.js.ExperimentalWasmJsInterop

@OptIn(
    ExperimentalWasmJsInterop::class,
    ExperimentalComposeUiApi::class
)
fun main() {
    initKoin()
    ComposeViewport {
        App()
    }
}
