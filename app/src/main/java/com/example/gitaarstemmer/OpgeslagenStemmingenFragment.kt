package com.example.gitaarstemmer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitaarstemmer.databinding.FragmentOpgeslagenStemmingenBinding

class OpgeslagenStemmingenFragment : Fragment() {

    private lateinit var binding: FragmentOpgeslagenStemmingenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpgeslagenStemmingenBinding.inflate(layoutInflater)

        return binding.root
    }
}