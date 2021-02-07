package ru.ekhalikov.homework2.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.model.Movie

class MovieAdapter(var onClickListener: View.OnClickListener): RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        view.setOnClickListener(onClickListener)
        return MovieViewHolder(view)
    }

    fun setData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size;
    }
}