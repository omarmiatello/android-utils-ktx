# EasyFRC (Easy Firebase Remote Config)

[![GitHub license](https://img.shields.io/github/license/omarmiatello/android-utils-ktx)](../LICENSE)

Simple configuration: Variables for [Firebase Remote Config](https://firebase.google.com/docs/remote-config)

## Setup

Add this in your `build.gradle.ktx` file:
```kotlin
implementation("com.github.omarmiatello.android-utils-ktx:easyfrc:1.1.0")
```

## How to use

Create an object `FRC` and declare properties.

Example ([see also "sample" folder](../sample/src/main/java/com/github/omarmiatello/androidutilsktx/example)):

- Define properties

```kotlin
object FRC : EasyFRC(devMode = BuildConfig.DEBUG) {
    val myBoolean by boolean(false)
    val myInt by int(1337)
    val myLong by long(134567890123456789)
    val myDouble by double(0.0)
    val myString by string("Ciao")
    // ...
    val secretFeature1_enabled by boolean(false)
}
```

- Use it (works everywhere)

```kotlin
if (FRC.secretFeature1_enabled) {
    // Feature 1 enabled! Try it now!
    
}
```

> Open "Firebase console" > "Remote Config"
>
> Url: https://console.firebase.google.com/

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
