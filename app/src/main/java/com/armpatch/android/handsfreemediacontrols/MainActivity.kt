package com.armpatch.android.handsfreemediacontrols

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var progressBar: ProgressBar
    private lateinit var imageCycler: ImageCycler
    private lateinit var progressAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageCycler = ImageCycler(findViewById(R.id.image_cycler))
        setupProgressbar()

        setPresenter(MainPresenter(this, this))
        presenter.onCreate()
    }

    private fun setupProgressbar() {
        progressBar = findViewById(R.id.progress_circle)
        progressBar.max = 100
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        progressAnimator.duration = 1000
        progressAnimator.interpolator = LinearInterpolator()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun startCycling() {
        progressAnimator.start()
        imageCycler.startCycling()
    }

    override fun selectCurrentActionIcon() {
        imageCycler.stopCycling()

        progressAnimator.cancel()
        progressBar.progress = 0
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

}
