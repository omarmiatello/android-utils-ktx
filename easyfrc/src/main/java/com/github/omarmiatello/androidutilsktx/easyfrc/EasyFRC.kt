package com.github.omarmiatello.androidutilsktx.easyfrc

import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class EasyFRC(private val devMode: Boolean) {

    fun fetch(): Task<Boolean> {
        // The default value expiration duration is 12 hours
        val fetch = if (devMode) frc.fetch(0) else frc.fetch()
        return fetch.continueWith { it.isSuccessful }
    }

    fun fetchAndActivate(onActivate: (() -> Unit)?) =
        fetch()
            .continueWithTask { frc.activate() }
            .addOnCompleteListener { onActivate?.invoke() }

    fun fetchRequestFromPush(): Task<Boolean> =
        frc.fetch(0)
            .continueWithTask { frc.activate() }

    // Delegate methods

    protected fun string(defaultValue: String? = null, key: String? = null) =
        FRCDelegate(defaultValue, key) { frc.getString(it) }

    protected fun boolean(defaultValue: Boolean = false, key: String? = null) =
        FRCDelegate(defaultValue, key) { frc.getBoolean(it) }

    protected fun double(defaultValue: Double = 0.0, key: String? = null) =
        FRCDelegate(defaultValue, key) { frc.getDouble(it) }

    protected fun long(defaultValue: Long = 0L, key: String? = null) =
        FRCDelegate(defaultValue, key) { frc.getLong(it) }

    protected fun int(defaultValue: Int = 0, key: String? = null) =
        FRCDelegate(defaultValue, key) { frc.getLong(it).toInt() }

    override fun toString(): String =
        (_defaults + (frc.getKeysByPrefix("").map { it to frc.getString(it) }
            .toMap())).toSortedMap().toString()

    // Private

    private val _defaults = HashMap<String, Any?>()

    private val frc by lazy {
        FirebaseRemoteConfig.getInstance().apply {
            setDefaultsAsync(_defaults)
                .continueWithTask { activate() }
                .continueWithTask { if (devMode) fetch(0) else fetch() }
        }
    }

    protected class FRCDelegate<T>(
        private val defaultValue: T,
        private val keyFRC: String?,
        private val getMethod: (String) -> T
    ) : ReadOnlyProperty<EasyFRC, T> {
        var key: String by Delegates.notNull()

        operator fun provideDelegate(thisRef: EasyFRC, property: KProperty<*>): FRCDelegate<T> {
            // init "key" here => throw exception if getValue() is called before provideDelegate()
            key = keyFRC ?: property.name
            thisRef._defaults[key] = defaultValue
            return this
        }

        override fun getValue(thisRef: EasyFRC, property: KProperty<*>) = getMethod(key)
    }
}
