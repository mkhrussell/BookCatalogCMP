package com.kamrul.bookcatalog

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.kamrul.bookcatalog.di.initKoin
import io.ktor.client.engine.js.Js
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule

@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("@js-joda/timezone")
external object JsJodaTimeZoneModule

private val jsJodaTz = JsJodaTimeZoneModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    ComposeViewport {
        App()
    }
}
