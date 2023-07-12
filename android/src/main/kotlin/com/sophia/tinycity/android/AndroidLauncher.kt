package com.sophia.tinycity.android

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.sophia.tinycity.TinyCityGame

/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(TinyCityGame(), AndroidApplicationConfiguration().apply {
            // Configure your application here.
        })
    }
}
