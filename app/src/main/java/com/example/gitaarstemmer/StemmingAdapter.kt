package com.example.gitaarstemmer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gitaarstemmer.databinding.ActivityMainBinding

class StemmingAdapter(val items: MutableList<Stemming>) :
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
                items.removeAt(position)
                notifyDataSetChanged() //FIXME: notifyItemRemoved(position) zorgt voor crash
            }
            // laad knop
            findViewById<Button>(R.id.buttonLaad).setOnClickListener {
                //TODO: laadknop
            }
        }
    }

    override fun getItemCount(): Int = items.size

}