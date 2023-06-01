package com.example.gitaarstemmer

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitaarstemmer.databinding.FragmentStemmerBinding


class StemmerFragment(var stemmingenLijst: ArrayList<Stemming>) : Fragment() {

    private lateinit var binding: FragmentStemmerBinding

    // thread
    private val mainHandler = Handler(Looper.getMainLooper())
    private var active: Boolean = false // is dit fragment bezig

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStemmerBinding.inflate(layoutInflater)
        active = true

        var stemming: Stemming? = stemmingenLijst.firstOrNull { it.selected } // kan null zijn
        if (stemming == null) {
            binding.stemmerTitel.text = getString(R.string.no_stemming_selected_message)
            binding.snarenNotatie.text = ""
        } else {
            binding.stemmerTitel.text = stemming.title
            binding.snarenNotatie.text = snarenNotatieText(stemming, 0)
        }

        createThread(stemming).start()

        return binding.root
    }

    override fun onDestroyView() {
        active = false // stopt de thread
        super.onDestroyView()
    }

    private fun snarenNotatieText(stemming: Stemming, hoeveelJuisteNoten: Int): Spanned {
        var returnText: String = ""
        var green = "#32CD32"
        var standard = "@android:color/tab_indicator_text"
        for (i in 0..5) {
            // bron: https://stackoverflow.com/a/30859757
            if (i < hoeveelJuisteNoten) {
                returnText += "<font color=" + green + ">" + stemming.noten[i].toString() + "</font> "
            } else {
                returnText += "<font color=" + standard + ">" + stemming.noten[i].toString() + "</font> "
            }

        }
        return Html.fromHtml(returnText)
    }

    private fun createThread(stemming: Stemming?): Thread {
        return Thread {
            val noteFinder = NoteFinder()
            val noten = arrayOf("A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#")

            // voor de feedback
            var tel = 0 // telt hoevaak de juiste noot is gespeeld
            var hoeveelsteNoot = 0 // de noot die nu gecheckt wordt

            // thread loop
            while (active) {
                var frequency = noteFinder.getNote()
                if (stemming != null && hoeveelsteNoot < 6) {
                    if (noten[frequency[0]] == stemming.noten[hoeveelsteNoot].toString() && frequency[1] == 0) {
                        if (tel++ == 2) {
                            tel = 0
                            hoeveelsteNoot++
                        }
                    } else {
                        tel = 0
                    }
                }

                mainHandler.post {
                    if (stemming != null) {
                        binding.snarenNotatie.text = snarenNotatieText(stemming, hoeveelsteNoot);
                    }
                    if (frequency[1] == 0) {
                        binding.background.setBackgroundColor(Color.GREEN)
                    } else {
                        binding.background.setBackgroundColor(Color.TRANSPARENT)
                    }
                    binding.noteTeller.text = noten[frequency[0]]
                    binding.noteOffset.text = frequency[1].toString()
                }
                Thread.sleep(750)
            }
        }
    }


}