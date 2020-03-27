package com.armpatch.android.handsfreemediacontrols.overlay

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
import android.view.WindowManager.LayoutParams.TYPE_PHONE
import androidx.annotation.LayoutRes
import com.armpatch.android.handsfreemediacontrols.GLOBAL_TAG

abstract class BaseOverlay(val context: Context, @LayoutRes layoutResId:  Int) {
    private var windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private lateinit var layoutParams: WindowManager.LayoutParams
    lateinit var overlayView: View
    private var isAttached: Boolean = false

    init {
        setLayout(context, layoutResId)
        setDefaultParams()
    }

    private fun setDefaultParams() {
        layoutParams = WindowManager.LayoutParams()

        layoutParams.y = -StatusBarHeight(context)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = ScreenHeight(context) + NavBarHeight(context) + StatusBarHeight(context)
        layoutParams.type =
            if (Build.VERSION.SDK_INT >= 26) TYPE_APPLICATION_OVERLAY else TYPE_PHONE
        layoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        layoutParams.format = PixelFormat.TRANSPARENT
        layoutParams.gravity = Gravity.TOP or Gravity.LEFT
    }

    fun showOverlay() {
        if (!isAttached) {
            windowManager.addView(overlayView, layoutParams)
            isAttached = true
            Log.d(GLOBAL_TAG, "showOverlay()")
        }
    }

    fun hideOverlay() {
        if (isAttached) {
            windowManager.removeView(overlayView)
            isAttached = false
            Log.d(GLOBAL_TAG, "hideOverlay()")
        }
    }

    private fun setLayout(context: Context, @LayoutRes layout: Int) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        overlayView = inflater.inflate(layout, null)
    }

}