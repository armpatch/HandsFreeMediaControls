package com.armpatch.android.handsfreemediacontrols.overlay

import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.armpatch.android.handsfreemediacontrols.GLOBAL_TAG
import com.armpatch.android.handsfreemediacontrols.R

class MediaViewCycler(container: View) {

    private val playPauseTrackImage: ImageView = container.findViewById(R.id.pause_icon)
    private val skipTrackImage: ImageView = container.findViewById(R.id.next_icon)
    private val previousTrackImage: ImageView = container.findViewById(R.id.back_icon)
    private val mediaImages = listOf(playPauseTrackImage, skipTrackImage, previousTrackImage)
    private var imageIndex = -1

    private val myHandler: Handler = Handler()
    private val transitionDelay: Long = 800
    private var actionRequested = false
    private var cycling = false

    private var actionListener: Listener? = null

    interface Listener {
        fun playPause()
        fun nextTrack()
        fun previousTrack()
    }

    private val imageChangingRunnable = Runnable {
        Log.d(GLOBAL_TAG, "imageChangingRunnable : Start")

        if (!actionRequested) {
            showNextImage()
            if (imageIndex < mediaImages.size) {
                changeImageAfterDelay(transitionDelay)
            }
        }
    }

    fun setActionListener(actionListener: Listener) {
        this.actionListener = actionListener
    }

    fun startCyclingActions() {
        Log.d(GLOBAL_TAG, "startCyclingActions")
        cycling = true
        actionRequested = false

        imageIndex = -1
        changeImageAfterDelay(0)
    }

    fun stopCycling() {
        if (cycling) {
            Log.d(GLOBAL_TAG, "stopCyclingActions")
            cycling = false
            actionRequested = true
            hideAllImages()
            sendActionCallback()
        }
    }

    private fun changeImageAfterDelay (delay: Long) {
        myHandler.postDelayed(imageChangingRunnable, delay)
    }

    private fun showNextImage() {
        imageIndex++

        hideAllImages()
        if (imageIndex < mediaImages.size) {
            mediaImages[imageIndex].visibility = View.VISIBLE
        }
    }

    private fun sendActionCallback() {
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
        actionListener?.playPause()
    }

    private fun nextTrack() {
        actionListener?.nextTrack()
    }

    private fun previousTrack() {
        actionListener?.previousTrack()
    }

}