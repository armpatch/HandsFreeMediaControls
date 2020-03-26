package com.armpatch.android.handsfreemediacontrols.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armpatch.android.handsfreemediacontrols.R
import com.armpatch.android.handsfreemediacontrols.service.OverlayService
import com.armpatch.android.handsfreemediacontrols.util.getOverlayPermission

class MainActivity : AppCompatActivity(), MainContract.View{

    private lateinit var presenter: MainContract.Presenter
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton = findViewById(R.id.start_service_button)
        startButton.setOnClickListener { startOverlayService() }

        setPresenter(MainPresenter(this))
        presenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        getOverlayPermission(this)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun startOverlayService() {
        if (Settings.canDrawOverlays(this)) {
            startService(Intent(this, OverlayService::class.java))
        } else {
            Toast.makeText(this,"Must Grant Permissions", Toast.LENGTH_SHORT).show()
        }
    }
}
