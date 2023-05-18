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

class OpgeslagenStemmingenFragment(var stemmingenLijst: ArrayList<Stemming>, var selectedStemming: Int) : Fragment() {

    private lateinit var binding: FragmentOpgeslagenStemmingenBinding
    private lateinit var adapter: StemmingAdapter
    private lateinit var fileRepository: FileRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOpgeslagenStemmingenBinding.inflate(layoutInflater)
        fileRepository = FileRepository(requireContext())

        adapter = StemmingAdapter(stemmingenLijst, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        return binding.root
    }

    fun deleteStemming(position: Int) {
        // bevestigings scherm
        var builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.alert_title)
        builder.setMessage(R.string.alert_message)
        // ja knop
        builder.setPositiveButton(
            R.string.alert_positive,
            DialogInterface.OnClickListener { dialog, which ->
                if (stemmingenLijst[position].selected) {
                    selectedStemming = -1
                }
                stemmingenLijst.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, adapter.itemCount - position)
                fileRepository.save(stemmingenLijst)
                dialog.cancel()
            })
        // annuleer knop
        builder.setNegativeButton(
            R.string.alert_negative,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
        // toont pop-up
        builder.create()
        builder.show()
    }

    fun selectStemming(position: Int) {
        // als er al een stemming is geselecteerd, -1 is nog niks geselecteerd
        if (selectedStemming >= 0) {
            stemmingenLijst[selectedStemming].selected = false
            adapter.notifyItemChanged(selectedStemming)
        }
        // selecteerd de stemming op position
        selectedStemming = position
        stemmingenLijst[position].selected = true
        adapter.notifyItemChanged(position)
        fileRepository.save(stemmingenLijst)
    }
}