package com.kamrul.bookcatalog

import androidx.compose.ui.window.ComposeUIViewController
import com.kamrul.bookcatalog.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
