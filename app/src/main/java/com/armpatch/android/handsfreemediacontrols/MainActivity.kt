package com.armpatch.android.handsfreemediacontrols

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var progressCircle: ProgressBar
    private lateinit var image: CyclingImageView
    private lateinit var progressAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)

        setupProgressCircle()

        setPresenter(MainPresenter(this, this))
        presenter.onCreate()
    }

    private fun setupProgressCircle() {
        progressCircle = findViewById(R.id.progress_circle)
        progressCircle.max = 100
        progressAnimator = ObjectAnimator.ofInt(progressCircle, "progress", 0, 100)
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
        progressCircle.progress = 0
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

}
