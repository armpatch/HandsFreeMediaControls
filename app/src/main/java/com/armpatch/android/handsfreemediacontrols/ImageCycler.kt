package com.armpatch.android.handsfreemediacontrols

import android.os.Handler
import android.view.View
import android.widget.ImageView

class ImageCycler(container: View) {

    private val pauseIcon: ImageView = container.findViewById(R.id.pause_icon)
    private val nextIcon: ImageView = container.findViewById(R.id.pause_icon)
    private val backIcon: ImageView = container.findViewById(R.id.pause_icon)

    private val iconDelay: Long = 1000

    private val myHandler: Handler = Handler()

    private val runnable1 = Runnable {
        pauseIcon.visibility = View.INVISIBLE
        nextIcon.visibility = View.VISIBLE
        myHandler.postDelayed(runnable2, iconDelay)
    }

    private val runnable2 = Runnable {
        nextIcon.visibility = View.VISIBLE
        backIcon.visibility = View.INVISIBLE
        myHandler.postDelayed(runnable3, iconDelay)
    }

    private val runnable3 = Runnable {
        backIcon.visibility = View.INVISIBLE
    }

    fun startCycling() {
        pauseIcon.visibility = View.VISIBLE
        myHandler.postDelayed(runnable1, iconDelay)
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

