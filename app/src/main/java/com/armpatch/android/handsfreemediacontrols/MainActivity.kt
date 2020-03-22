package com.armpatch.android.handsfreemediacontrols

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var progressBar: ProgressBar
    private lateinit var image: CyclingImageView
    private lateinit var progressAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)

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

    override fun cycleToNextActionIcon() {
        progressAnimator.start()
        image.cycleImage()
    }

    override fun selectCurrentActionIcon() {
        progressAnimator.cancel()
        progressBar.progress = 0
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

}
