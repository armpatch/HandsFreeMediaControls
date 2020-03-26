package com.armpatch.android.handsfreemediacontrols.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun getOverlayPermission(activityContext: Context) {
    if (!Settings.canDrawOverlays(activityContext)) {
        val permissionIntent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$activityContext.packageName")
        )
        activityContext.startActivity(permissionIntent)
    }
}