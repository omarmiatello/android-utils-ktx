package com.github.omarmiatello.androidutilsktx.commonintent

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings

// https://developer.android.com/guide/components/intents-common.html
object CommonIntent {

    /**
     * Text Messaging - https://developer.android.com/guide/components/intents-common.html#Messaging
     */
    fun composeSmsMessage(message: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.type = "text/plain"
        intent.putExtra("sms_body", message)
        //    intent.putExtra(Intent.EXTRA_STREAM, attachment)
        return intent
    }

    /**
     * Email - https://developer.android.com/guide/components/intents-common.html#Email
     */
    fun composeEmail(addresses: Array<String>, subject: String, text: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }

    fun openMarket(appPackage: String) =
        Uri.parse("market://details?id=$appPackage").toViewIntent().launchAdjacent()

    fun openAppDetailSetting(appPackage: String) =
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:$appPackage"))
            .launchAdjacent()
}

private fun Intent.launchAdjacent() = if (Build.VERSION.SDK_INT >= 24) {
    addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT)
} else this

/**
 * http://developer.android.com/training/basics/intents/sending.html#Verify
 */
fun Intent?.isIntentSafe(context: Context) = if (this != null) {
    context.packageManager.queryIntentActivities(this, PackageManager.MATCH_DEFAULT_ONLY).size > 0
} else false

/**
 * http://developer.android.com/reference/android/content/Intent.html#ACTION_VIEW
 */
fun Uri.toViewIntent() = Intent(Intent.ACTION_VIEW).setData(this)