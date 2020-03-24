package com.armpatch.android.handsfreemediacontrols

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
import android.view.WindowManager.LayoutParams.TYPE_PHONE

abstract class BaseOverlay(context: Context) {
    private var windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private lateinit var layoutParams: WindowManager.LayoutParams
    private lateinit var overlayView: View
    private var isAttached: Boolean = false

    init {
        setDefaultParams()
    }

    fun detachFromWindow() {
        if (isAttached) {
            windowManager.removeView(overlayView)
            isAttached = false
        }
    }

    fun attachToWindow() {
        if (!isAttached) {
            windowManager.addView(overlayView, layoutParams)
            isAttached = true
        }
    }

    fun setDefaultParams() {
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.type =
            if (Build.VERSION.SDK_INT >= 26) TYPE_APPLICATION_OVERLAY else TYPE_PHONE
        layoutParams.flags =
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        layoutParams.format = PixelFormat.TRANSPARENT
        layoutParams.gravity = Gravity.TOP or Gravity.LEFT
    }

}