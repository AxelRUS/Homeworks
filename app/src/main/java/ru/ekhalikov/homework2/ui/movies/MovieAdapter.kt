package ru.ekhalikov.homework2.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.model.Movie

class MovieAdapter(var onClickListener: View.OnClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        view.setOnClickListener(onClickListener)
        return ViewHolder(view)
    }

    fun setData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var image: ImageView = view.findViewById(R.id.ivMovie)
        var title: TextView = view.findViewById(R.id.tvMovieName)
        var length: TextView = view.findViewById(R.id.tvMovieLength)

        fun bind(movie: Movie) {
//        image.load()
            title.text = movie.title
        }
    }
}