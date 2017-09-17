package com.github.jacklt.jutils.example

import android.util.Log
import com.github.jacklt.jutils.BuildConfig
import com.github.jacklt.jutils.easyfrc.EasyFRC
import java.util.concurrent.TimeUnit

object FRC : EasyFRC(devMode = BuildConfig.DEBUG) {

    // Basic example

    val myBoolean by boolean(false)
    val myInt by int(1337)
    val myLong by long(134567890123456789)
    val myDouble by double(0.0)
    val myString by string("Ciao")
    val myByteArray by byteArray(ByteArray(0))


    // Real world examples

    val signup_useHint by boolean(true)
    val secretFeature1_enabled by boolean(false)
    val secretFeature2_enabled by boolean(true)
    val cache_expireMax by long(TimeUnit.DAYS.toSeconds(7))
    val logMaxLines by int(10)
    val logLevel by int(Log.INFO)

    init {
        Log.i("FRC", toString())
    }
}
