package com.armpatch.android.handsfreemediacontrols.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class OverlayService: Service(), ServiceContract.View, MediaViewCycler.Listener{

    private lateinit var presenter: ServiceContract.Presenter
    private lateinit var mediaViewCycler: MediaViewCycler

    override fun onBind(intent: Intent?): IBinder? = null

    fun setup() {

        TODO("create overlay and init mediaViewCycler")

        setPresenter(ServicePresenter(this, this))
        presenter.onCreate()
    }

    override fun setPresenter(presenter: ServiceContract.Presenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun startCycling() {
        mediaViewCycler.startCyclingActions()
    }

    override fun stopCycling() {
        mediaViewCycler.stopCycling()
    }

    override fun playPause() {
        presenter.mediaPlayPause()
    }

    override fun nextTrack() {
        presenter.mediaNextTrack()
    }

    override fun previousTrack() {
        presenter.mediaPreviousTrack()
    }
}