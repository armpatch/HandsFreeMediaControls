package com.armpatch.android.handsfreemediacontrols.activity

import android.content.Context

class MainPresenter(view: MainContract.View, private val activityContext: Context)
    : MainContract.Presenter {
    private var view: MainContract.View? = view

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}