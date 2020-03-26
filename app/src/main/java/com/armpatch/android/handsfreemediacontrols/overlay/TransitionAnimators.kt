package com.armpatch.android.handsfreemediacontrols.overlay

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.LinearInterpolator

    fun fadeInAnimator(view: View, duration: Long): ObjectAnimator {
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(view, alpha)

        animator.interpolator = LinearInterpolator()
        animator.duration = duration

        return animator
    }
