package com.github.jacklt.jutils.commonintent


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings

/**
 * Created on 02/07/15, 18:08
 */

private fun Intent.launchAdjacent() = apply { if (Build.VERSION.SDK_INT >= 24) flags = Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT }

// http://developer.android.com/training/basics/intents/sending.html#Verify
fun Intent?.isIntentSafe(context: Context) = if (this == null) false else context.packageManager.queryIntentActivities(this, PackageManager.MATCH_DEFAULT_ONLY).size > 0


// https://developer.android.com/guide/components/intents-common.html

// Text Messaging - https://developer.android.com/guide/components/intents-common.html#Messaging
fun composeSmsMessage(message: String): Intent {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.type = "text/plain"
    intent.putExtra("sms_body", message)
    //    intent.putExtra(Intent.EXTRA_STREAM, attachment)
    return intent
}

// Email - https://developer.android.com/guide/components/intents-common.html#Email
fun composeEmail(addresses: Array<String>, subject: String, text: String): Intent {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:") // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, addresses)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, text)
    return intent
}

// http://developer.android.com/reference/android/content/Intent.html#ACTION_VIEW
fun Uri.viewIntent() = Intent(Intent.ACTION_VIEW).setData(this)

fun openMarket(appPackage: String) = Uri.parse("market://details?id=$appPackage").viewIntent().launchAdjacent()

fun openAppDetailSetting(appPackage: String) = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:$appPackage")).launchAdjacent()