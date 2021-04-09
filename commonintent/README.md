# CommonIntent

[![GitHub license](https://img.shields.io/github/license/omarmiatello/android-utils-ktx)](../LICENSE)
[![](https://img.shields.io/maven-central/v/com.github.omarmiatello.android-utils-ktx/commonintent)](https://search.maven.org/search?q=g:com.github.omarmiatello.android-utils-ktx)

[Common intents](https://developer.android.com/guide/components/intents-common.html) for Android

## Setup

Add this in your `build.gradle.ktx` file:
```kotlin
implementation("com.github.omarmiatello.android-utils-ktx:commonintent:1.1.0")
```

## How to use

```kotlin
// CommonIntents
val smsIntent = CommonIntent.composeSmsMessage(message = "Hi!")
val emailIntent = CommonIntent.composeEmail(addresses = arrayOf("a@a.a"), subject = "Hi!", text = "Message")
val playStoreIntent = CommonIntent.openMarket(appPackage = "com.package.myapp")
val settingIntent = CommonIntent.openAppDetailSetting(appPackage = "com.package.myapp")

// Utility: Intent.isIntentSafe()
val isSafe: Boolean = smsIntent.isIntentSafe(context)

// Utility: Uri to Intent
val uriIntent = "https://www.example.com".toUri().toViewIntent()
val uriIntent2 = Uri.parse("https://www.example.com").toViewIntent()
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
