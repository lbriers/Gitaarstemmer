package com.example.gitaarstemmer

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.gitaarstemmer.databinding.ActivityWelcomeBinding
import com.google.android.material.snackbar.Snackbar
import android.Manifest
import androidx.core.app.ActivityCompat

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)

        // remove title bar
        supportActionBar?.hide()

        val intent = Intent(this, MainActivity::class.java)

        binding.startButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, perform your task here
                startActivity(intent)
            } else {
                // Permission is not granted, request it
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO), 1)
            }
        }


        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent)
            }
        }
    }
}