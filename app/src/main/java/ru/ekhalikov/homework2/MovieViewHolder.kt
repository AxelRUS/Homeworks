package ru.ekhalikov.homework2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    var movie: ImageView = view.findViewById(R.id.ivMovie)
    var name: TextView = view.findViewById(R.id.tvMovieName)
    var length: TextView = view.findViewById(R.id.tvMovieLength)

    fun bind() {

    }
}