package com.example.gitaarstemmer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitaarstemmer.databinding.FragmentMaakStemmingenBinding


class MaakStemmingenFragment : Fragment() {

    private lateinit var binding: FragmentMaakStemmingenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMaakStemmingenBinding.inflate(layoutInflater)

        return binding.root
    }


}