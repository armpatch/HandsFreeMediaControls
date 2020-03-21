package com.armpatch.android.handsfreemediacontrols

interface MainContract {
    interface Presenter : BasePresenter {
        //TODO may action to be called from view
    }

    interface View : BaseView<Presenter> {
        fun cycleToNextActionIcon()
        fun selectCurrentActionIcon()
    }
}