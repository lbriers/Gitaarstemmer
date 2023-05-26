package com.example.gitaarstemmer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gitaarstemmer.databinding.FragmentStemmerBinding


class StemmerFragment : Fragment() {

    private lateinit var binding: FragmentStemmerBinding
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStemmerBinding.inflate(layoutInflater)

        //stemmerThread.start()

        return binding.root
    }
    /*
    val stemmerThread = Thread {
        val noteFinder = NoteFinder()
        val noten = arrayOf("A", "A#" , "B", "C" , "C#", "D" , "D#", "E", "F" , "F#", "G" , "G#")
        while (true) {
            frequency = noteFinder.getNote()
            mainHandler.post {
                binding.noteTeller.text = noten[frequency[0]] + " " + frequency[1].toString()
            }
            Thread.sleep(750)
        }
    }*/

}