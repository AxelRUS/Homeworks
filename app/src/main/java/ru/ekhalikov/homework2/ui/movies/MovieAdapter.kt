package ru.ekhalikov.homework2.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ekhalikov.homework2.R

class MovieAdapter(var onClickListener: View.OnClickListener): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        view.setOnClickListener(onClickListener)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 4;
    }
}