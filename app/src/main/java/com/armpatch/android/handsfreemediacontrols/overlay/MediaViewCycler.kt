package com.armpatch.android.handsfreemediacontrols.overlay

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
    private val imageResourcesIds = intArrayOf(
        R.drawable.ic_play_pause,
        R.drawable.ic_skip_next,
        R.drawable.ic_skip_previous,
        R.drawable.ic_cancel_x
    )
    private var imageIndex = -1

    private val myHandler: Handler = Handler()
    private val transitionTime: Long = 600
    private var actionRequested = false
    private var isCycling = false

    private var mediaListener: MediaActionListener? = null
    private var animationListener: AnimationListener? = null

    private var fadeInAnimation = fadeInAnimator(container, 200)
    private var fadeOutAnimation = fadeOutAnimator(container, 200)

    init {
        fadeInAnimation.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                startCyclingAnimation()
            }
        })
        fadeOutAnimation.startDelay = 800
        fadeOutAnimation.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                reset()
                animationListener?.onAnimationEnded()
            }
        })
    }

    interface MediaActionListener {
        fun onPlayPauseAction()
        fun onNextTrackAction()
        fun onPreviousTrackAction()
    }

    /**
     * used for when all media actions have been shown without an action being selected
     */
    interface AnimationListener {
        fun onAnimationEnded()
    }

    private val imageChangingRunnable = Runnable {
        Log.d(GLOBAL_TAG, "imageChangingRunnable : Start")

        if (actionRequested) return@Runnable

        imageIndex++

        if (imageIndex < 3) {
            mediaImage.setImageResource(imageResourcesIds[imageIndex])
            Log.d(GLOBAL_TAG, "imageChangingRunnable : set image at index = $imageIndex")
        }

        if (imageIndex < 3) {
            changeMediaIcon(transitionTime)
        } else {
            sendMediaAction()
        }
    }

    fun setMediaListener(mediaListener: MediaActionListener) {
        this.mediaListener = mediaListener
    }

    fun setAnimationListener(animationListener: AnimationListener) {
        this.animationListener = animationListener
    }

    fun start() {
        Log.d(GLOBAL_TAG, "Media Cycler: startCycling")
        isCycling = true
        actionRequested = false

        fadeInAnimation.start()
    }

    private fun startCyclingAnimation() {
        circularProgress.progress = 0f
        circularProgress.setProgressWithAnimation(100f, 3 * transitionTime, LinearInterpolator(), 0)
        imageIndex = -1
        changeMediaIcon(0)
    }

    fun stop() {
        if (isCycling) {
            Log.d(GLOBAL_TAG, "Media Cycler: stop")
            isCycling = false
            actionRequested = true

            circularProgress.indeterminateMode = true

            sendMediaAction()
        }
    }

    private fun changeMediaIcon (delay: Long) {
        myHandler.postDelayed(imageChangingRunnable, delay)
    }

    private fun sendMediaAction() {
        Log.d(GLOBAL_TAG, "send media Action, index - $imageIndex")
        when (imageIndex) {
            0 -> mediaListener?.onPlayPauseAction()
            1 -> mediaListener?.onNextTrackAction()
            2 -> mediaListener?.onPreviousTrackAction()
            3 -> fadeOutAnimation.startDelay = 0
        }
        isCycling = false
        fadeOutAnimation.start()
    }

    private fun reset() {
        mediaImage.setImageResource(imageResourcesIds[0])
        circularProgress.indeterminateMode = false
        circularProgress.progress = 0f
        fadeOutAnimation.startDelay = 800
    }

}