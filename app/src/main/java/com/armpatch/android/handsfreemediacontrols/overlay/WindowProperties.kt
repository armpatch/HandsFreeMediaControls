package com.armpatch.android.handsfreemediacontrols.overlay

import android.app.Service
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

fun StatusBarHeight(context: Context): Int {
    val resourceId = context.getResources().getIdentifier(
        "status_bar_height", "dimen", "android"
    )

    return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
}

fun ScreenHeight(context: Context) = displayMetrics(context).heightPixels

fun NavBarHeight(context: Context): Int {
    val resources = context.resources
    val resourceId = resources.getIdentifier(
        "navigation_bar_height",
        "dimen", "android"
    )

    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}

private fun displayMetrics(context: Context): DisplayMetrics {
    val wManager = context.getSystemService(Service.WINDOW_SERVICE) as WindowManager
    val dMetrics =  DisplayMetrics()

    wManager.defaultDisplay.getMetrics(dMetrics)
    return dMetrics
}