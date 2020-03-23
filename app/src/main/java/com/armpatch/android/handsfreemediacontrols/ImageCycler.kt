package com.armpatch.android.handsfreemediacontrols

import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView

class ImageCycler(container: View) {

    private val playPauseImage: ImageView = container.findViewById(R.id.pause_icon)
    private val nextImage: ImageView = container.findViewById(R.id.next_icon)
    private val backImage: ImageView = container.findViewById(R.id.back_icon)
    private var currentlyVisibleImage: ImageView? = null

    private val myHandler: Handler = Handler()
    private val transitionDelay: Long = 1000

    private var actionRequested = false
    private var cycling = false

    private val showMediaNext = Runnable {
        Log.d(GLOBAL_TAG, "--------Next")

        if (actionRequested) {
            playPause()
        } else {
            show(nextImage)
            myHandler.postDelayed(showMediaBack, transitionDelay)
        }
    }

    private val showMediaBack = Runnable {
        Log.d(GLOBAL_TAG, "------------------Back")

        if (actionRequested) {
            nextTrack()
        } else {
            show(backImage)
            myHandler.postDelayed(hideMediaBack, transitionDelay)
        }
    }

    private val hideMediaBack = Runnable {
        if (actionRequested) {
            previousTrack()
        } else {
            hideAllImages()
        }
    }

    fun startCycling() {
        Log.d(GLOBAL_TAG, "Pause")
        cycling = true
        actionRequested = false
        show(playPauseImage)
        myHandler.postDelayed(showMediaNext, transitionDelay)
    }

    fun stopCycling() {
        if (cycling) {
            Log.d(GLOBAL_TAG, "----    ACTION SELECTED    ----")
            cycling = false
            actionRequested = true
        }
    }

    private fun show(current: ImageView) {
        currentlyVisibleImage = current

        hideAllImages()

        current.visibility = View.VISIBLE
    }

    private fun hideAllImages() {
        playPauseImage.visibility = View.INVISIBLE
        nextImage.visibility = View.INVISIBLE
        backImage.visibility = View.INVISIBLE
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




//    private val scaleX: PropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 0f, 1.0f)
//    private val scaleY: PropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 0f, 1.0f)
//    private val sizeAnimator: ObjectAnimator =
//        ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY)


//    private val animatorSet: AnimatorSet = AnimatorSet()


    /*   Handler handler = new Handler();
       int delay = 1000; //milliseconds

       handler.postDelayed(new Runnable(){
           public void run(){
               //do something
               handler.postDelayed(this, delay);
           }
       }, delay);  */

}

