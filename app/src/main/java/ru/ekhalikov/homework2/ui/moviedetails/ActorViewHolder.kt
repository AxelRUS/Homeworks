package ru.ekhalikov.homework2.ui.moviedetails

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ekhalikov.homework2.R

class ActorViewHolder(item: View): RecyclerView.ViewHolder(item) {
    val image = item.findViewById<ImageView>(R.id.image)
    val name = item.findViewById<TextView>(R.id.name)

    fun bind(){

    }
}