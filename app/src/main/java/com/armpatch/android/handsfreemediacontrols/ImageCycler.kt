package com.armpatch.android.handsfreemediacontrols

import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView

class ImageCycler(container: View) {

    private val playPauseTrackImage: ImageView = container.findViewById(R.id.pause_icon)
    private val skipTrackImage: ImageView = container.findViewById(R.id.next_icon)
    private val previousTrackImage: ImageView = container.findViewById(R.id.back_icon)
    private var currentlyVisibleImage: ImageView? = null

    private val myHandler: Handler = Handler()
    private val transitionDelay: Long = 800

    private var actionRequested = false
    private var cycling = false

    private val showMediaNext = Runnable {
        Log.d(GLOBAL_TAG, "--------Next")

        if (actionRequested) {
            sendActionAssociatedWith(currentlyVisibleImage)
        } else {
            show(skipTrackImage)
            myHandler.postDelayed(showMediaBack, transitionDelay)
        }
    }

    private val showMediaBack = Runnable {
        Log.d(GLOBAL_TAG, "------------------Back")

        if (actionRequested) {
            sendActionAssociatedWith(currentlyVisibleImage)
        } else {
            show(previousTrackImage)
            myHandler.postDelayed(hideMediaBack, transitionDelay)
        }
    }

    private val hideMediaBack = Runnable {
        if (actionRequested) {
            sendActionAssociatedWith(currentlyVisibleImage)
        } else {
            hideAllImages()
        }
    }

    fun startCycling() {
        Log.d(GLOBAL_TAG, "Pause")
        cycling = true
        actionRequested = false
        show(playPauseTrackImage)
        myHandler.postDelayed(showMediaNext, transitionDelay)
    }

    fun stopCycling() {
        if (cycling) {
            Log.d(GLOBAL_TAG, "----    STOP CYCLING    ----")
            cycling = false
            actionRequested = true
        }
    }

    private fun show(current: ImageView) {
        currentlyVisibleImage = current

        hideAllImages()

        current.visibility = View.VISIBLE
    }

    private fun sendActionAssociatedWith(mediaImage: ImageView?) {
        hideAllImages()
        when (mediaImage) {
            playPauseTrackImage -> playPause()
            skipTrackImage -> nextTrack()
            previousTrackImage -> previousTrack()
        }
    }

    private fun hideAllImages() {
        playPauseTrackImage.visibility = View.INVISIBLE
        skipTrackImage.visibility = View.INVISIBLE
        previousTrackImage.visibility = View.INVISIBLE
    }

    private fun playPause() {
        Log.d(GLOBAL_TAG, "----    PLAY/PAUSE    ----")
    }

    private fun nextTrack() {
        Log.d(GLOBAL_TAG, "----       NEXT       ----")
    }

    private fun previousTrack() {
        Log.d(GLOBAL_TAG, "----       BACK       ----")
    }

}

