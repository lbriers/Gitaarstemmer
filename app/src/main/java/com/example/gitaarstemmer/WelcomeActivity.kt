package com.example.gitaarstemmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gitaarstemmer.databinding.ActivityWelcomeBinding
import com.google.android.material.snackbar.Snackbar

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)

        // remove title bar
        supportActionBar?.hide()

        val intent = Intent(this, MainActivity::class.java)

        binding.startButton.setOnClickListener {
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}