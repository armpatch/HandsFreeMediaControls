package com.armpatch.android.handsfreemediacontrols

import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView

class MediaActionSelector(container: View) {

    private val playPauseTrackImage: ImageView = container.findViewById(R.id.pause_icon)
    private val skipTrackImage: ImageView = container.findViewById(R.id.next_icon)
    private val previousTrackImage: ImageView = container.findViewById(R.id.back_icon)
    private val mediaImages = listOf(playPauseTrackImage, skipTrackImage, previousTrackImage)
    private var imageIndex = -1

    private val myHandler: Handler = Handler()
    private val transitionDelay: Long = 800
    private var actionRequested = false
    private var cycling = false

    interface MediaActionListener {
        fun playPause()
        fun nextTrack()
        fun previousTrack()
    }

    private val imageCycleRunnable = Runnable {
        Log.d(GLOBAL_TAG, "--------Universal Runnable start -------")

        if (!actionRequested) {
            showNextImage()
            if (imageIndex < mediaImages.size) {
                cycleImageAfterDelay(transitionDelay)
            }
        }
    }

    fun startCycling() {
        Log.d(GLOBAL_TAG, "Pause")
        cycling = true
        actionRequested = false

        imageIndex = -1
        cycleImageAfterDelay(0)
    }

    fun stopCycling() {
        if (cycling) {
            Log.d(GLOBAL_TAG, "----    STOP CYCLING    ----")
            cycling = false
            actionRequested = true
            broadcastAction()
        }
    }

    private fun cycleImageAfterDelay (delay: Long) {
        myHandler.postDelayed(imageCycleRunnable, delay)
    }

    private fun showNextImage() {
        imageIndex++

        hideAllImages()
        if (imageIndex < mediaImages.size) {
            mediaImages[imageIndex].visibility = View.VISIBLE
        }
    }

    private fun broadcastAction() {
        hideAllImages()
        when (imageIndex) {
            0 -> playPause()
            1 -> nextTrack()
            2 -> previousTrack()
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