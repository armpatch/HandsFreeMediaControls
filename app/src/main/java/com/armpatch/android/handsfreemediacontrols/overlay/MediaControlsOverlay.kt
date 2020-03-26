package com.armpatch.android.handsfreemediacontrols.overlay

import android.content.Context
import com.armpatch.android.handsfreemediacontrols.R

class MediaControlsOverlay(context: Context, mediaCyclerListener: MediaViewCycler.Listener):
    BaseOverlay(context, R.layout.content_overlay) {

    private var fadeInAnimator = fadeInAnimator(overlayView, 200)
    private var mediaViewCycler: MediaViewCycler = MediaViewCycler(overlayView)

    init {
        mediaViewCycler.setActionListener(mediaCyclerListener)
    }

    override fun showOverlay() {
        super.showOverlay()
        fadeInAnimator.start()
    }

    fun startCyclingMediaActions() {
        showOverlay()
        mediaViewCycler.startCyclingActions()
    }

    fun stopCyclingMediaActions() {
        mediaViewCycler.stopCycling()
        hideOverlay()
    }
}