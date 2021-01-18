package ru.ekhalikov.homework2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorViewHolder(item: View): RecyclerView.ViewHolder(item) {
    val image = item.findViewById<ImageView>(R.id.image)
    val name = item.findViewById<TextView>(R.id.name)

    fun bind(){

    }
}