package com.armpatch.android.handsfreemediacontrols

interface MainContract {
    interface Presenter : BasePresenter {
        // no methods yet
    }

    interface View : BaseView<Presenter> {
        fun startAction()
        fun stopAction()
    }
}