package ru.ekhalikov.homework2.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.model.Movie

class MovieListAdapter(private val onClickCard: (item: Movie) -> Unit) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item, onClickCard)
    }

    override fun getItemCount(): Int {
        return movies.size;
    }

    fun setData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var image: ImageView = view.findViewById(R.id.ivMovie)
        var title: TextView = view.findViewById(R.id.tvMovieName)
        var length: TextView = view.findViewById(R.id.tvMovieLength)

        fun bind(item: Movie, onClickCard: (item: Movie) -> Unit) {
//        image.load()
            title.text = item.title

            itemView.setOnClickListener {
                onClickCard(item)
            }
        }
    }
}