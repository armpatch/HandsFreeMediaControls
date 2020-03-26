package com.armpatch.android.handsfreemediacontrols.activity

import com.armpatch.android.handsfreemediacontrols.BasePresenter
import com.armpatch.android.handsfreemediacontrols.BaseView

interface MainContract {
    interface Presenter : BasePresenter {
        // nothing to do
    }

    interface View :
        BaseView<Presenter> {
        fun startOverlayService()
    }

}