package com.armpatch.android.handsfreemediacontrols.overlay

import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.armpatch.android.handsfreemediacontrols.GLOBAL_TAG
import com.armpatch.android.handsfreemediacontrols.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class MediaViewCycler(container: View) {

    private val circularProgress: CircularProgressBar = container.findViewById(R.id.circular_progress_bar)
    private val mediaImage: ImageView = container.findViewById(R.id.media_action_selector)

    private val mediaResourcesIds = intArrayOf(
        R.drawable.ic_pause_black_24dp,
        R.drawable.ic_skip_next_black_24dp,
        R.drawable.ic_skip_previous_black_24dp,
        R.drawable.ic_clear_black_24dp
    )

    private var imageIndex = -1

    private val myHandler: Handler = Handler()
    private val transitionTime: Long = 600
    private var actionRequested = false
    private var isCycling = false

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

        if (actionRequested) return@Runnable

        imageIndex++

        if (imageIndex < 4) {
            mediaImage.setImageResource(mediaResourcesIds[imageIndex])
            Log.d(GLOBAL_TAG, "imageChangingRunnable : set image at index = $imageIndex")
        }

        if (imageIndex < 3) {
            changeImageAfterDelay(transitionTime)
        } else {
            notifyExpired()
        }
    }

    fun setMediaListener(mediaListener: MediaActionListener) {
        this.mediaListener = mediaListener
    }

    fun setExpirationListener(expirationListener: ExpirationListener) {
        this.expirationListener = expirationListener
    }

    fun start() {
        Log.d(GLOBAL_TAG, "Media Cycler: startCycling")
        isCycling = true
        actionRequested = false

        circularProgress.indeterminateMode = false
        circularProgress.progress = 0f
        circularProgress.setProgressWithAnimation(100f, 3 * transitionTime, LinearInterpolator(), 0)
        imageIndex = -1
        changeImageAfterDelay(0)
    }

    fun stop() {
        if (isCycling) {
            Log.d(GLOBAL_TAG, "Media Cycler: stop")
            isCycling = false
            actionRequested = true

            circularProgress.indeterminateMode = true
            circularProgress.progress = 100f

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

    private fun notifyExpired() {
        isCycling = false
        expirationListener?.onMediaCyclerExpired()
    }
}