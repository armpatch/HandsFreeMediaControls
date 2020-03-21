package com.armpatch.android.handsfreemediacontrols

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var textView: TextView
    private lateinit var button: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text)
        button = findViewById(R.id.button)

        setPresenter(MainPresenter(this, this))
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun startAction() {
        button.isPressed = true
    }

    override fun stopAction() {
        button.isPressed = false
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

}
