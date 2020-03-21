package com.armpatch.android.handsfreemediacontrols

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class CyclingImageView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {

    private var currentItem: Int = 0
    private val resources = listOf(
        R.drawable.ic_pause_black_24dp,
        R.drawable.ic_skip_previous_black_24dp,
        R.drawable.ic_skip_next_black_24dp)

    fun cycleImage() {
        incrementItem()
        setImageResource(resources[currentItem])
    }

    private fun incrementItem() { if (currentItem == resources.size - 1) currentItem = 0 else currentItem++ }

}