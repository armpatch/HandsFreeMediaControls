package com.armpatch.android.handsfreemediacontrols

interface MainContract {
    interface Presenter : BasePresenter {
        // no methods yet
    }

    interface View : BaseView<Presenter> {
        fun startCyclingOptions()
        fun stopCyclingOptions()
    }
}