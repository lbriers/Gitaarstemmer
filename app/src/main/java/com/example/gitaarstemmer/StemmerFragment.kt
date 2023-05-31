package com.example.gitaarstemmer

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gitaarstemmer.databinding.FragmentStemmerBinding


class StemmerFragment(var stemmingenLijst: ArrayList<Stemming>) : Fragment() {

    private lateinit var binding: FragmentStemmerBinding
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        stemmerThread.start()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStemmerBinding.inflate(layoutInflater)

        var stemming: Stemming? = stemmingenLijst.firstOrNull{ it.selected } // kan null zijn
        if(stemming == null){
            binding.snarenNotatie.text = getString(R.string.no_stemming_selected_message)
        }
        else{
            var noten = stemming.noten
            binding.snarenNotatie.text = noten.get(0).toString() + " - " + noten.get(1).toString() +  " - " + noten.get(2).toString() +  " - " + noten.get(3).toString() +  " - " + noten.get(4).toString() +  " - " + noten.get(5).toString()

        }

        return binding.root
    }

    val stemmerThread = Thread {
        val noteFinder = NoteFinder()
        val noten = arrayOf("A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#")
        while (true) {
            var frequency = noteFinder.getNote()
            mainHandler.post {
                binding.noteTeller.text = noten[frequency[0]]
                binding.noteOffset.text = frequency[1].toString()
            }
            Thread.sleep(750)
        }
    }

}