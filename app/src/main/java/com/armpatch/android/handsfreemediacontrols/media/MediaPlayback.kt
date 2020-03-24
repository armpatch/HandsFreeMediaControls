package com.armpatch.android.handsfreemediacontrols.media

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.view.KeyEvent

class MediaPlayback(activityContext: Context){

    private val audioManager = activityContext.getSystemService(Activity.AUDIO_SERVICE) as AudioManager

    fun playPause() = sendEvent(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)

    fun nextTrack() = sendEvent(KeyEvent.KEYCODE_MEDIA_NEXT)

    fun previousTrack() = sendEvent(KeyEvent.KEYCODE_MEDIA_PREVIOUS)

    private fun sendEvent(keyEvent: Int) {
        val eventDown = KeyEvent(KeyEvent.ACTION_DOWN, keyEvent)
        val eventUp = KeyEvent(KeyEvent.ACTION_UP, keyEvent)

        audioManager.dispatchMediaKeyEvent(eventDown)
        audioManager.dispatchMediaKeyEvent(eventUp)
    }

}
