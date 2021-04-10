# SLF4J Logger for Android

[![GitHub license](https://img.shields.io/github/license/omarmiatello/android-utils-ktx)](../LICENSE)
[![](https://img.shields.io/maven-central/v/com.github.omarmiatello.android-utils-ktx/slf4j-logger)](https://search.maven.org/search?q=g:com.github.omarmiatello.android-utils-ktx)

Simple implementation of [SLF4J Logger](http://slf4j.org/manual.html) for Android

Note: Could be used with Ktor

## Setup

Add this in your `build.gradle.ktx` file:
```kotlin
implementation("com.github.omarmiatello.android-utils-ktx:slf4j-logger:1.1.0")
```

## How to use

```kotlin
abstract class BasicAndroidLogger(/* ... */) : org.slf4j.helpers.MarkerIgnoringBase() {
    // ...
}

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
```

## License

```
MIT License

Copyright (c) 2021 Omar Miatello

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
