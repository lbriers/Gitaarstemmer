package com.example.gitaarstemmer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StemmingAdapter(val items: List<Stemming>) : RecyclerView.Adapter<StemmingAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(currentItemView: View) : RecyclerView.ViewHolder(currentItemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stemming, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: StemmingAdapter.TodoViewHolder, position: Int) {
        val currentTodoItem = items[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.txtStemmingTitle).text = currentTodoItem.title
        }
    }

    override fun getItemCount(): Int = items.size

}