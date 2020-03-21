package com.armpatch.android.handsfreemediacontrols

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.view.KeyEvent

class MainPresenter(view: MainContract.View, private val activityContext: Context)
    : MainContract.Presenter {

    private var view: MainContract.View? = view

    private lateinit var audioManager: AudioManager

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor

    override fun onDestroy() {
        this.view = null
    }

    override fun onCreate() {
        setupAudioManager()
        setupProximitySensor()
    }

    private fun setupAudioManager() {
        audioManager = activityContext.getSystemService(Activity.AUDIO_SERVICE) as AudioManager
    }

    private fun setupProximitySensor() {
        sensorManager = activityContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorManager.registerListener(
            proximitySensorEventListener,
            proximitySensor,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    private var proximitySensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0f) {
                    onCloseProximity()
                } else {
                    onFarProximity()
                }
            }
        }
    }

    private fun onCloseProximity() {
        view?.startAction()
        toggleMediaPlayPause()
    }

    private fun onFarProximity() {
        view?.stopAction()
    }

    private fun toggleMediaPlayPause() {
        val eventDown = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)
        val eventUp = KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)

        audioManager.dispatchMediaKeyEvent(eventDown)
        audioManager.dispatchMediaKeyEvent(eventUp)
    }
}