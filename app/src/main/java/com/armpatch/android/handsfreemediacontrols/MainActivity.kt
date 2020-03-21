package com.armpatch.android.handsfreemediacontrols

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor

    private lateinit var textView: TextView
    private lateinit var button: ImageButton
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text)
        button = findViewById(R.id.button)

        setupAudioManager()
        setupProximitySensor()
    }

    private fun setupAudioManager() {
        audioManager = getSystemService(Activity.AUDIO_SERVICE) as AudioManager
    }

    private fun setupProximitySensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorManager.registerListener(
            proximitySensorEventListener,
            proximitySensor,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    private var proximitySensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {

                if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0f) {
                        onCloseProximity()
                    } else {
                        onFarProximity()
                    }
                }
            }
        }
    }

    private fun onCloseProximity() {
        button.isPressed  = true
        togglePlayPause()
    }

    private fun onFarProximity() {
        button.isPressed = false
    }

    private fun togglePlayPause() {
        val eventDown = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)
        val eventUp = KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)

        audioManager.dispatchMediaKeyEvent(eventDown)
        audioManager.dispatchMediaKeyEvent(eventUp)
    }
}
