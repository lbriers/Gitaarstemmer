package com.example.gitaarstemmer

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitaarstemmer.databinding.FragmentOpgeslagenStemmingenBinding

class OpgeslagenStemmingenFragment(
    var stemmingenLijst: ArrayList<Stemming>,
    var selectedStemming: Int) : Fragment() {

    private lateinit var binding: FragmentOpgeslagenStemmingenBinding
    private lateinit var adapter: StemmingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpgeslagenStemmingenBinding.inflate(layoutInflater)

        adapter = StemmingAdapter(stemmingenLijst, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        return binding.root
    }

    fun deleteStemming(position: Int) {
        var builder = AlertDialog.Builder(activity) // bevestigings scherm
        builder.setTitle(R.string.alert_title)
        builder.setMessage(R.string.alert_message)
        builder.setPositiveButton( // ja knop
            R.string.alert_positive,
            DialogInterface.OnClickListener { dialog, which ->
                if (stemmingenLijst[position].selected) {
                    selectedStemming = -1
                }
                stemmingenLijst.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, adapter.itemCount - position)
                dialog.cancel()
            })
        builder.setNegativeButton( // annuleer knop
            R.string.alert_negative,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
        builder.create()
        builder.show()
    }

    fun selectStemming(position: Int) {
        if (selectedStemming >= 0) { // -1 is nog niks geselecteerd
            stemmingenLijst[selectedStemming].selected = false
            adapter.notifyItemChanged(selectedStemming)
        }
        selectedStemming = position
        stemmingenLijst[position].selected = true
        adapter.notifyItemChanged(position)
    }
}