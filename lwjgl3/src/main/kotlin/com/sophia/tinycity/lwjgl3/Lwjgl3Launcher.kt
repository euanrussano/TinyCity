@file:JvmName("Lwjgl3Launcher")

package com.sophia.tinycity.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.sophia.tinycity.TinyCityGame

/** Launches the desktop (LWJGL3) application. */
fun main() {
    Lwjgl3Application(TinyCityGame(true), Lwjgl3ApplicationConfiguration().apply {
        setTitle("TinyCity")
        setWindowedMode(1280, 720)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
