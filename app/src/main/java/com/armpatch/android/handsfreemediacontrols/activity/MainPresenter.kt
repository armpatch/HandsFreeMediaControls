package com.armpatch.android.handsfreemediacontrols.activity

class MainPresenter(view: MainContract.View)
    : MainContract.Presenter {
    private var view: MainContract.View? = view

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}