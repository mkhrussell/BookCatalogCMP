package com.kamrul.bookcatalog

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.kamrul.bookcatalog.di.initKoin
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(
        engine = remember { Darwin.create() }
    )
}
