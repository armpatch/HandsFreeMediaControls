package com.armpatch.android.handsfreemediacontrols.overlay

import android.content.Context
import com.armpatch.android.handsfreemediacontrols.R

class MediaControlsOverlay(context: Context, mediaCyclerListener: MediaViewCycler.Listener):
    BaseOverlay(context, R.layout.content_overlay) {

    private var mediaViewCycler: MediaViewCycler =
        MediaViewCycler(overlayView)

    init {
        mediaViewCycler.setActionListener(mediaCyclerListener)
    }

    fun startCyclingMediaActions() {
        show()
        mediaViewCycler.startCyclingActions()
    }

    fun stopCyclingMediaActions() {
        mediaViewCycler.stopCycling()
        hide()
    }
}