package uk.usedcars.marketplace.dealers.auto.finance.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.content.ActivityNotFoundException

object RateAppUtils {
    fun rateApp(context: Context) {
        val packageName = context.packageName
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
        }
    }
}
