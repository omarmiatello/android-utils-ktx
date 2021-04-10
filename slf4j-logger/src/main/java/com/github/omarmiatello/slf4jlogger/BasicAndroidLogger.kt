package com.github.omarmiatello.slf4jlogger

import android.util.Log
import org.slf4j.helpers.MarkerIgnoringBase
import org.slf4j.helpers.MessageFormatter

abstract class BasicAndroidLogger(
    internal val levelMin: Int = Log.DEBUG,
) : MarkerIgnoringBase() {

    abstract fun logV(msg: String, tr: Throwable? = null)

    abstract fun logD(msg: String, tr: Throwable? = null)

    abstract fun logI(msg: String, tr: Throwable? = null)

    abstract fun logW(msg: String, tr: Throwable? = null)

    abstract fun logE(msg: String, tr: Throwable? = null)

    override fun isTraceEnabled() = levelMin >= Log.VERBOSE

    override fun isDebugEnabled() = levelMin >= Log.DEBUG

    override fun isInfoEnabled() = levelMin >= Log.INFO

    override fun isWarnEnabled() = levelMin >= Log.WARN

    override fun isErrorEnabled() = levelMin >= Log.ERROR

    override fun trace(msg: String) = logV(msg)

    override fun trace(format: String, arg: Any) =
        logV(MessageFormatter.format(format, arg).message)

    override fun trace(format: String, arg1: Any, arg2: Any) =
        logV(MessageFormatter.format(format, arg1, arg2).message)

    override fun trace(format: String, vararg arguments: Any) =
        logV(MessageFormatter.format(format, arguments).message)

    override fun trace(msg: String, t: Throwable) =
        logV(msg, t)

    override fun debug(msg: String) =
        logD(msg)

    override fun debug(format: String, arg: Any) =
        logD(MessageFormatter.format(format, arg).message)

    override fun debug(format: String, arg1: Any, arg2: Any) =
        logD(MessageFormatter.format(format, arg1, arg2).message)

    override fun debug(format: String, vararg arguments: Any) =
        logD(MessageFormatter.format(format, arguments).message)

    override fun debug(msg: String, t: Throwable) =
        logD(msg, t)

    override fun info(msg: String) =
        logI(msg)

    override fun info(format: String, arg: Any) =
        logI(MessageFormatter.format(format, arg).message)

    override fun info(format: String, arg1: Any, arg2: Any) =
        logI(MessageFormatter.format(format, arg1, arg2).message)

    override fun info(format: String, vararg arguments: Any) =
        logI(MessageFormatter.format(format, arguments).message)

    override fun info(msg: String, t: Throwable) =
        logI(msg, t)

    override fun warn(msg: String) =
        logW(msg)

    override fun warn(format: String, arg: Any) =
        logW(MessageFormatter.format(format, arg).message)

    override fun warn(format: String, arg1: Any, arg2: Any) =
        logW(MessageFormatter.format(format, arg1, arg2).message)

    override fun warn(format: String, vararg arguments: Any) =
        logW(MessageFormatter.format(format, arguments).message)

    override fun warn(msg: String, t: Throwable) =
        logW(msg, t)

    override fun error(msg: String) =
        logE(msg)

    override fun error(format: String, arg: Any) =
        logE(MessageFormatter.format(format, arg).message)

    override fun error(format: String, arg1: Any, arg2: Any) =
        logE(MessageFormatter.format(format, arg1, arg2).message)

    override fun error(format: String, vararg arguments: Any) =
        logE(MessageFormatter.format(format, arguments).message)

    override fun error(msg: String, t: Throwable) =
        logE(msg, t)
}