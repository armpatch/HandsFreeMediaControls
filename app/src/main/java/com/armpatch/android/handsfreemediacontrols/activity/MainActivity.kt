package com.armpatch.android.handsfreemediacontrols.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armpatch.android.handsfreemediacontrols.R

class MainActivity : AppCompatActivity(), MainContract.View{

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPresenter(MainPresenter(this, this))
        presenter.onCreate()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
