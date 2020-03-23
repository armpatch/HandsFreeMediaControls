package com.armpatch.android.handsfreemediacontrols

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainContract.View, MediaActionSelector.Listener{

    private lateinit var presenter: MainContract.Presenter
    private lateinit var mediaActionSelector: MediaActionSelector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaActionSelector = MediaActionSelector(findViewById(R.id.media_action_selector))
        mediaActionSelector.setActionListener(this)

        setPresenter(MainPresenter(this, this))
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun startCycling() {
        mediaActionSelector.startCyclingActions()
    }

    override fun stopCycling() {
        mediaActionSelector.stopCycling()
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

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

}
