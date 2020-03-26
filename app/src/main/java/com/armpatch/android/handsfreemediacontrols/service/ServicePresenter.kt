package com.armpatch.android.handsfreemediacontrols.service

import android.content.Context
import com.armpatch.android.handsfreemediacontrols.media.MediaPlayback
import com.armpatch.android.handsfreemediacontrols.sensors.ProximitySensor

class ServicePresenter(view: ServiceContract.View, private val activityContext: Context) :
    ServiceContract.Presenter,
    ProximitySensor.Listener {

    private var view: ServiceContract.View? = view

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
        view?.startCyclingMediaActions()
    }

    override fun onFarProximity() {
        view?.stopCyclingMediaActions()
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