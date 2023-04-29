package com.example.gitaarstemmer

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StemmingAdapter(
    val items: MutableList<Stemming>,
    val fragment: OpgeslagenStemmingenFragment
) :
    RecyclerView.Adapter<StemmingAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(currentItemView: View) : RecyclerView.ViewHolder(currentItemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_stemming, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: StemmingAdapter.TodoViewHolder, position: Int) {
        val currentTodoItem = items[position]
        holder.itemView.apply {
            // titel
            findViewById<TextView>(R.id.txtStemmingTitle).text = currentTodoItem.title
            // delete knop
            findViewById<Button>(R.id.buttonDelete).setOnClickListener {
                fragment.deleteStemming(position) // TODO: vragen of juist is
            }
            // laad knop
            findViewById<Button>(R.id.buttonLaad).setOnClickListener {
                fragment.selectStemming(position)
            }
            if (items[position].selected) {
                this.setBackgroundColor(Color.LTGRAY)
            } else {
                setBackgroundColor(Color.WHITE)
            }

        }
    }

    override fun getItemCount(): Int = items.size

}