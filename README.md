# JUtils
Utils in Kotlin for Android Developer :)

[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/jacklt/jutils/master/LICENSE)


## Setup EasyFRC (Easy Firebase Remote Config)

Grab via Gradle (v4 or later):
```groovy
implementation 'com.github.jacklt.jutils:easyfrc:1.0.1'
```
or Gradle (before v4):
```groovy
compile 'com.github.jacklt.jutils:easyfrc:1.0.1'
```

NOTE:
- Add Firebase to your project:

Open Menu "Tools" > "Firebase": 

- Double check dependencies: for FRC and Kotlin
```groovy
implementation 'com.google.firebase:firebase-config:11.2.2'
implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
```

## How to use

Create an object `FRC` and declare properties.

Example ([see also "sample" folder](sample/src/main/java/com/github/jacklt/jutils/example)):

- Define properties
```kotlin
object FRC : EasyFRC(devMode = BuildConfig.DEBUG) {
    val myBoolean by boolean(false)
    val myInt by int(1337)
    val myLong by long(134567890123456789)
    val myDouble by double(0.0)
    val myString by string("Ciao")
    val myByteArray by byteArray(ByteArray(0))
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

    Copyright 2017 Omar Miatello.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    All trademarks and registered trademarks are the property of their respective owners.