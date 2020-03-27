package com.armpatch.android.handsfreemediacontrols.overlay

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import com.armpatch.android.handsfreemediacontrols.R

class MediaControlsOverlay(context: Context, mediaCyclerListener: MediaViewCycler.MediaActionListener):
    BaseOverlay(context, R.layout.content_overlay), MediaViewCycler.ExpirationListener {

    private var fadeInAnimator = fadeInAnimator(overlayView, 200)
    private var fadeOutAnimator = fadeOutAnimator(overlayView, 200)
    private var mediaViewCycler: MediaViewCycler = MediaViewCycler(overlayView)

    init {
        mediaViewCycler.setMediaListener(mediaCyclerListener)
        mediaViewCycler.setExpirationListener(this)

        fadeOutAnimator.startDelay = 500
        fadeOutAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) { hideOverlay() }
        })
    }

    fun startCyclingMediaActions() {
        showOverlay()
        fadeInAnimator.start()
        mediaViewCycler.startCyclingActions()
    }

    fun stopCyclingMediaActions() {
        mediaViewCycler.stopCycling()
        animateHide()
    }

    override fun onMediaCyclerExpired() {
        animateHide()
    }

    private fun animateHide() {
        fadeInAnimator.cancel()
        fadeOutAnimator.start()
    }
}