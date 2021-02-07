package ru.ekhalikov.homework2.ui.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ekhalikov.homework2.R

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    var movie: ImageView = view.findViewById(R.id.ivMovie)
    var name: TextView = view.findViewById(R.id.tvMovieName)
    var length: TextView = view.findViewById(R.id.tvMovieLength)

    fun bind() {

    }
}