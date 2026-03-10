package com.kamrul.bookcatalog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform