package com.armpatch.android.handsfreemediacontrols.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.armpatch.android.handsfreemediacontrols.R
import com.armpatch.android.handsfreemediacontrols.service.OverlayService

class MainActivity : AppCompatActivity(), MainContract.View{

    private lateinit var presenter: MainContract.Presenter
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton = findViewById(R.id.start_service_button)
        startButton.setOnClickListener { startOverlayService() }

        setPresenter(MainPresenter(this, this))
        presenter.onCreate()

        requestOverlayPermission()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun requestOverlayPermission() {
        val permissionIntent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$packageName")
        )
        startActivity(permissionIntent)
    }

    override fun startOverlayService() {
        startService(Intent(this, OverlayService::class.java))
    }
}
