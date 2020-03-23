package com.armpatch.android.handsfreemediacontrols

import android.content.Context

class MainPresenter(view: MainContract.View, private val activityContext: Context)
    : MainContract.Presenter, ProximitySensor.ProximityListener {

    private var view: MainContract.View? = view

    private lateinit var proximitySensor: ProximitySensor
    private lateinit var mediaPlayback: MediaPlayback

    override fun onDestroy() {
        this.view = null
    }

    override fun onCreate() {
        proximitySensor = ProximitySensor(activityContext, this)
        mediaPlayback = MediaPlayback(activityContext)
    }

    override fun onCloseProximity() {
        view?.startCycling()
    }

    override fun onFarProximity() {
        view?.stopCycling()
    }

    override fun mediaPlayPause() {
        mediaPlayback.playPause()
    }

    override fun mediaNextTrack() {
        mediaPlayback.nextTrack()
    }

    override fun mediaPreviousTrack() {
        mediaPlayback.previousTrack()
    }
}