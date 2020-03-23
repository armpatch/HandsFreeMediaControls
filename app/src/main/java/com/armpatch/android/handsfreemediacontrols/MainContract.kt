package com.armpatch.android.handsfreemediacontrols

interface MainContract {
    interface Presenter : BasePresenter {
        fun mediaPlayPause()
        fun mediaNextTrack()
        fun mediaPreviousTrack()
    }

    interface View : BaseView<Presenter> {
        fun startCycling()
        fun stopCycling()
    }
}