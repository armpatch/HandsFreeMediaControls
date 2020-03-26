package com.armpatch.android.handsfreemediacontrols.service

import com.armpatch.android.handsfreemediacontrols.BasePresenter
import com.armpatch.android.handsfreemediacontrols.BaseView

interface ServiceContract {
    interface Presenter : BasePresenter {
        fun mediaPlayPause()
        fun mediaNextTrack()
        fun mediaPreviousTrack()
    }

    interface View :
        BaseView<Presenter> {
        fun startCyclingMediaActions()
        fun stopCyclingMediaActions()
    }
}