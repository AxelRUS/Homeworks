package ru.ekhalikov.homework2.presentation.movies

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.models.Movie

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

        private val image: ImageView = view.findViewById(R.id.ivMovie)
        private val title: TextView = view.findViewById(R.id.tvMovieName)
        private val length: TextView = view.findViewById(R.id.tvMovieLength)
        private val likeImage: ImageView = view.findViewById(R.id.ivLike)
        private val reviews: TextView = view.findViewById(R.id.tvReviewsCount)
        private val pgRaiting: TextView = view.findViewById(R.id.tvPG)

        private val starsImages: List<ImageView> = listOf(
            itemView.findViewById(R.id.ivStarOne),
            itemView.findViewById(R.id.ivStarTwo),
            itemView.findViewById(R.id.ivStarThree),
            itemView.findViewById(R.id.ivStarFour),
            itemView.findViewById(R.id.ivStarFive)
        )

        fun bind(item: Movie, onClickCard: (item: Movie) -> Unit) {
            Glide
                .with(itemView.context.applicationContext)
                .load(item.imageUrl)
                .into(image)

            val likeColor = if (item.isLiked) {
                R.color.pink_light
            } else {
                R.color.white
            }
            ImageViewCompat.setImageTintList(
                likeImage, ColorStateList.valueOf(
                    ContextCompat.getColor(likeImage.context, likeColor)
                )
            )

            starsImages.forEachIndexed { index, imageView ->
                val colorId = if (item.rating > index) R.color.pink_light else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }


            val context = itemView.context

            title.text = item.title
            pgRaiting.text = "${item.pgAge}+"
            length.text = "${item.runningTime} MIN"
            reviews.text =
                context.getString(R.string.movies_list_reviews_template, item.reviewCount)

            itemView.setOnClickListener {
                onClickCard(item)
            }
        }
    }
}