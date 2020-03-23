package com.armpatch.android.handsfreemediacontrols

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ProximitySensor(private val activityContext: Context, private val listener: Listener) {

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor
    private val proximityEventListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0f) {
                    listener.onCloseProximity()
                } else {
                    listener.onFarProximity()
                }
            }
        }
    }

    interface Listener {
        fun onCloseProximity()
        fun onFarProximity()
    }

    init {
        setupProximitySensor()
    }

    private fun setupProximitySensor() {
        sensorManager = activityContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)


        sensorManager.registerListener(
            proximityEventListener,
            proximitySensor,
            SensorManager.SENSOR_DELAY_UI
        )
    }

}