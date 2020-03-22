package com.armpatch.android.handsfreemediacontrols

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.view.KeyEvent

class MainPresenter(view: MainContract.View, private val activityContext: Context)
    : MainContract.Presenter, ProximitySensor.ProximityListener {

    private var view: MainContract.View? = view

    private lateinit var audioManager: AudioManager
    private lateinit var proximitySensor: ProximitySensor

    override fun onDestroy() {
        this.view = null
    }

    override fun onCreate() {
        setupAudioManager()
        proximitySensor = ProximitySensor(activityContext, this)
    }

    private fun setupAudioManager() {
        audioManager = activityContext.getSystemService(Activity.AUDIO_SERVICE) as AudioManager
    }

    override fun onCloseProximity() {
        view?.cycleToNextActionIcon()
        //toggleMediaPlayPause()
    }

    override fun onFarProximity() {
        view?.selectCurrentActionIcon()
    }

    private fun toggleMediaPlayPause() {
        val eventDown = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)
        val eventUp = KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)

        audioManager.dispatchMediaKeyEvent(eventDown)
        audioManager.dispatchMediaKeyEvent(eventUp)
    }
}