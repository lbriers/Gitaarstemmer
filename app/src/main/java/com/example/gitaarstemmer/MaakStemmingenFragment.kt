package com.example.gitaarstemmer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gitaarstemmer.databinding.FragmentMaakStemmingenBinding


class MaakStemmingenFragment(var stemmingenLijst: ArrayList<Stemming>) : Fragment() {


    private lateinit var binding: FragmentMaakStemmingenBinding
    private lateinit var snaren: Array<Spinner>
    private var geselecteerdeNoten = arrayOf(Noot.A, Noot.A, Noot.A, Noot.A, Noot.A, Noot.A)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMaakStemmingenBinding.inflate(layoutInflater)

        snaren = arrayOf(
            binding.spinnerSnaar1,
            binding.spinnerSnaar2,
            binding.spinnerSnaar3,
            binding.spinnerSnaar4,
            binding.spinnerSnaar5,
            binding.spinnerSnaar6
        )

        // spinners voor de snaren initializeren
        for (snaar in snaren) {
            // Create an ArrayAdapter
            var arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Noot.values())
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            snaar.adapter = arrayAdapter

            snaar.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    geselecteerdeNoten[snaren.indexOf(snaar)] =
                        parentView?.getItemAtPosition(position) as Noot
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {}
            })
        }

        // knop
        binding.maakStemmingButton.setOnClickListener {
            if (binding.titelInput.getText().toString() == "") { // geen titel
                Toast.makeText(
                    context,
                    R.string.geen_titel_waarschuwing,
                    Toast.LENGTH_LONG
                ).show()

            } else {
                stemmingenLijst.add(
                    Stemming(
                        binding.titelInput.getText().toString(),
                        false,
                        geselecteerdeNoten.copyOf()
                    )
                )
                Toast.makeText(context, R.string.succesful_save, Toast.LENGTH_LONG).show()
            }

        }

        return binding.root
    }

}