package com.armpatch.android.handsfreemediacontrols

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.view.KeyEvent

class MediaPlayback(activityContext: Context){

    private val audioManager = activityContext.getSystemService(Activity.AUDIO_SERVICE) as AudioManager

    fun playPause() {
        val eventDown = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)
        val eventUp = KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)

        audioManager.dispatchMediaKeyEvent(eventDown)
        audioManager.dispatchMediaKeyEvent(eventUp)
    }

}
