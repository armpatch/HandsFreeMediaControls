package com.armpatch.android.handsfreemediacontrols

class MainPresenter(view: MainContract.View)
    : MainContract.Presenter {

    private var view: MainContract.View? = view

    override fun onDestroy() {
        this.view = null
    }
}