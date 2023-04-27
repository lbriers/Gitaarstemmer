package com.example.gitaarstemmer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitaarstemmer.databinding.FragmentOpgeslagenStemmingenBinding

class OpgeslagenStemmingenFragment : Fragment() {

    private lateinit var binding: FragmentOpgeslagenStemmingenBinding
    private lateinit var adapter: StemmingAdapter
    private  val stemmingenLijst: ArrayList<Stemming> = arrayListOf<Stemming>(Stemming("test1"), Stemming("test2"), Stemming("test3"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpgeslagenStemmingenBinding.inflate(layoutInflater)

        adapter = StemmingAdapter(stemmingenLijst)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        return binding.root
    }
}