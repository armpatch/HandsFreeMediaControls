package com.armpatch.android.handsfreemediacontrols.overlay

import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.armpatch.android.handsfreemediacontrols.GLOBAL_TAG
import com.armpatch.android.handsfreemediacontrols.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class MediaViewCycler(container: View) {

    private val circularProgress: CircularProgressBar = container.findViewById(R.id.circular_progress_bar)
    // used 3 images instead of changing the resource of one image.
    // TODO change to have one image instead
    private val playPauseTrackImage: ImageView = container.findViewById(R.id.pause_icon)
    private val skipTrackImage: ImageView = container.findViewById(R.id.next_icon)
    private val previousTrackImage: ImageView = container.findViewById(R.id.back_icon)
    private val mediaImages = listOf(playPauseTrackImage, skipTrackImage, previousTrackImage)
    private var imageIndex = -1

    private val myHandler: Handler = Handler()
    private val transitionTime: Long = 800
    private var actionRequested = false
    private var cycling = false

    private var mediaListener: MediaActionListener? = null
    private var expirationListener: ExpirationListener? = null

    interface MediaActionListener {
        fun onPlayPauseAction()
        fun onNextTrackAction()
        fun onPreviousTrackAction()
    }

    /**
     * used for when all media actions have been shown without an action being selected
     */
    interface ExpirationListener {
        fun onMediaCyclerExpired()
    }

    private val imageChangingRunnable = Runnable {
        Log.d(GLOBAL_TAG, "imageChangingRunnable : Start")

        if (!actionRequested) {
            imageIndex++

            hideAllImages()
            if (imageIndex < mediaImages.size) {
                mediaImages[imageIndex].visibility = View.VISIBLE
                circularProgress.progress = 0f
                circularProgress.setProgressWithAnimation(100f, transitionTime, LinearInterpolator(), 0)
                changeImageAfterDelay(transitionTime)
            } else {
                expirationListener?.onMediaCyclerExpired()
            }
        }
    }

    fun setMediaListener(mediaListener: MediaActionListener) {
        this.mediaListener = mediaListener
    }

    fun setExpirationListener(expirationListener: ExpirationListener) {
        this.expirationListener = expirationListener
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
            circularProgress.setProgressWithAnimation(circularProgress.progress + 10, 200, DecelerateInterpolator() )
            notifyMediaListener()
        }
    }

    private fun changeImageAfterDelay (delay: Long) {
        myHandler.postDelayed(imageChangingRunnable, delay)
    }
    private fun notifyMediaListener() {
        when (imageIndex) {
            0 -> mediaListener?.onPlayPauseAction()
            1 -> mediaListener?.onNextTrackAction()
            2 -> mediaListener?.onPreviousTrackAction()
        }
    }

    private fun hideAllImages() {
        playPauseTrackImage.visibility = View.INVISIBLE
        skipTrackImage.visibility = View.INVISIBLE
        previousTrackImage.visibility = View.INVISIBLE
    }

}