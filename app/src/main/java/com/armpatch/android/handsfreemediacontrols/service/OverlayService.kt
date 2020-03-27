package com.armpatch.android.handsfreemediacontrols.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.armpatch.android.handsfreemediacontrols.overlay.MediaControlsOverlay
import com.armpatch.android.handsfreemediacontrols.overlay.MediaViewCycler

class OverlayService : Service(), ServiceContract.View, MediaViewCycler.MediaActionListener {

    private lateinit var presenter: ServiceContract.Presenter
    private lateinit var mediaControlsOverlay: MediaControlsOverlay

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        setPresenter(ServicePresenter(this, this))
        presenter.onCreate()

        mediaControlsOverlay = MediaControlsOverlay(applicationContext, this)
    }

    override fun setPresenter(presenter: ServiceContract.Presenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun startCyclingMediaActions() {
        mediaControlsOverlay.startCyclingMediaActions()
    }

    override fun stopCyclingMediaActions() {
        mediaControlsOverlay.stopCyclingMediaActions()
    }

    override fun onPlayPauseAction() {
        presenter.mediaPlayPause()
    }

    override fun onNextTrackAction() {
        presenter.mediaNextTrack()
    }

    override fun onPreviousTrackAction() {
        presenter.mediaPreviousTrack()
    }
}