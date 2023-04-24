package com.example.gitaarstemmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gitaarstemmer.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding test
        binding.button.setOnClickListener { view ->
            Snackbar.make(view, "Yippie!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

    }
}