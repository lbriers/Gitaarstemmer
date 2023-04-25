package com.example.gitaarstemmer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitaarstemmer.databinding.FragmentWelcomeScreenBinding
import com.google.android.material.snackbar.Snackbar


class WelcomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeScreenBinding.inflate(layoutInflater)

        binding.button.setOnClickListener { view ->
            Snackbar.make(view, "Yippie!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

        return binding.root
    }

}