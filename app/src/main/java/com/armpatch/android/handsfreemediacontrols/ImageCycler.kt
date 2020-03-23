package com.armpatch.android.handsfreemediacontrols

import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView

class ImageCycler(container: View) {

    private val pauseIcon: ImageView = container.findViewById(R.id.pause_icon)
    private val nextIcon: ImageView = container.findViewById(R.id.next_icon)
    private val backIcon: ImageView = container.findViewById(R.id.back_icon)

    private val myHandler: Handler = Handler()
    private val iconDelay: Long = 1000

    private val showMediaNext = Runnable {
        Log.d(GLOBAL_TAG, "--------Next")
        pauseIcon.visibility = View.INVISIBLE
        nextIcon.visibility = View.VISIBLE
        myHandler.postDelayed(showMediaBack, iconDelay)
    }

    private val showMediaBack = Runnable {
        Log.d(GLOBAL_TAG, "------------------Back")
        nextIcon.visibility = View.INVISIBLE
        backIcon.visibility = View.VISIBLE
        myHandler.postDelayed(hideMediaBack, iconDelay)
    }

    private val hideMediaBack = Runnable {
        backIcon.visibility = View.INVISIBLE
    }

    fun startCycling() {
        Log.d(GLOBAL_TAG, "Pause")
        pauseIcon.visibility = View.VISIBLE
        myHandler.postDelayed(showMediaNext, iconDelay)
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

