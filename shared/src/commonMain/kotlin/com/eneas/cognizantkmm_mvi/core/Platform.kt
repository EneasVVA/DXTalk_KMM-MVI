package com.eneas.cognizantkmm_mvi.core

enum class PlatformType(platform: String) {
    IOS("iOS"),
    ANDROID("Android")
}

expect val Platform: PlatformType