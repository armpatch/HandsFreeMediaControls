package com.armpatch.android.handsfreemediacontrols.overlay

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import com.armpatch.android.handsfreemediacontrols.R

class MediaControlsOverlay(context: Context, mediaCyclerListener: MediaViewCycler.MediaActionListener):
    BaseOverlay(context, R.layout.layout_overlay_screen), MediaViewCycler.ExpirationListener {

    private var fadeInAnimator = fadeInAnimator(overlayView, 200)
    private var fadeOutAnimator = fadeOutAnimator(overlayView, 200)
    private var mediaViewCycler: MediaViewCycler = MediaViewCycler(overlayView)

    private var isVisible: Boolean = false

    init {
        mediaViewCycler.setMediaListener(mediaCyclerListener)
        mediaViewCycler.setExpirationListener(this)

        fadeOutAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) { hideOverlay() }
        })
    }

    fun startCyclingMediaActions() {
        if (!isVisible) {
            isVisible = true
            showOverlay()
            fadeInAnimator.start()
            mediaViewCycler.start()
        }
    }

    fun stopCyclingMediaActions() {
        if (isVisible) {
            mediaViewCycler.stop()
            fadeOutAnimator.startDelay = 500
            animateHide()
            isVisible = false
        }
    }

    override fun onMediaCyclerExpired() {
        fadeOutAnimator.startDelay = 100
        animateHide()
    }

    private fun animateHide() {
        fadeInAnimator.cancel()
        fadeOutAnimator.start()
    }
}