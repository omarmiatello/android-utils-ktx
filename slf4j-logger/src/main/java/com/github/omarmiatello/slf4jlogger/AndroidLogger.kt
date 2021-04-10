package com.github.omarmiatello.slf4jlogger

import android.util.Log

class AndroidLogger(
    levelMin: Int = Log.DEBUG,
    private val tag: String = AndroidLogger::class.java.simpleName,
) : BasicAndroidLogger(levelMin) {

    override fun logV(msg: String, tr: Throwable?) {
        Log.v(tag, msg, tr)
    }

    override fun logD(msg: String, tr: Throwable?) {
        Log.d(tag, msg, tr)
    }

    override fun logI(msg: String, tr: Throwable?) {
        Log.i(tag, msg, tr)
    }

    override fun logW(msg: String, tr: Throwable?) {
        Log.w(tag, msg, tr)
    }

    override fun logE(msg: String, tr: Throwable?) {
        Log.e(tag, msg, tr)
    }
}