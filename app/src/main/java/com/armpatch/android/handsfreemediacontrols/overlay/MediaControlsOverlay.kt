package com.armpatch.android.handsfreemediacontrols.overlay

import android.content.Context
import com.armpatch.android.handsfreemediacontrols.R

class MediaControlsOverlay(context: Context, mediaCyclerListener: MediaViewCycler.MediaActionListener):
    BaseOverlay(context, R.layout.layout_overlay_screen), MediaViewCycler.AnimationListener {

    private var mediaViewCycler: MediaViewCycler = MediaViewCycler(overlayView)

    private var isVisible: Boolean = false

    init {
        mediaViewCycler.setMediaListener(mediaCyclerListener)
        mediaViewCycler.setAnimationListener(this)
    }

    fun startCyclingMediaActions() {
        if (!isVisible) {
            isVisible = true
            showOverlay()
            mediaViewCycler.start()
        }
    }

    fun stopCyclingMediaActions() {
        if (isVisible) {
            mediaViewCycler.stop()
            isVisible = false
        }
    }

    // activated when MediaViewCycler is done animating and gone
    override fun onAnimationEnded() {
        hideOverlay()
    }
}