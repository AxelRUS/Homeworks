package ru.ekhalikov.homework2.ui.moviedetails

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.appComponent
import ru.ekhalikov.homework2.models.Actor
import ru.ekhalikov.homework2.models.Movie

class MovieDetailsFragment : Fragment() {

    private lateinit var viewModel: MovieDetailsViewModel
    private var movieId = -1

    var listener: MovieDetailsBackClickListener? = null
    private var actorsRecycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieDetailsBackClickListener) {
            listener = context
        }

        if (context is AppCompatActivity) {
            context.supportActionBar?.apply {
                title = getString(R.string.movie_details)
                setHomeAsUpIndicator(R.drawable.ic_chevron_left)
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = arguments?.getSerializable(PARAM_MOVIE_ID) as? Int ?: return

        actorsRecycler = view.findViewById<RecyclerView>(R.id.rvActors)
        actorsRecycler?.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            this.adapter = ActorListAdapter()
            setupViewModel()
        }

//        lifecycleScope.launch {
//            val repository =
//                (context?.applicationContext as MovieRepositoryProvider).provideMovieRepository()
//            val movie = repository.loadMovie(movieId)
//
//            if (movie != null) {
//                bindUI(view, movie)
//            } else {
//                showMovieNotFoundError()
//            }
//        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, appComponent().viewModelFactory())
            .get(MovieDetailsViewModel::class.java)
        viewModel.movie.observe(
            this@MovieDetailsFragment.viewLifecycleOwner
        ) {
            updateMovieDetailsInfo(it)
            updateActors(it.actors)
        }

        if (movieId > 0) {
            viewModel.onViewCreated(movieId)
        }
    }

    private fun updateActors(actors: List<Actor>) {
        if (actors.isNotEmpty()) {
            (actorsRecycler?.adapter as? ActorListAdapter)?.setData(actors)
        }
    }


    private fun showMovieNotFoundError() {
        Toast.makeText(requireContext(), R.string.error_movie_not_found, Toast.LENGTH_LONG)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                listener?.onMovieDeselected()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

//    private fun bindUI(view: View, movie: Movie) {
//        updateMovieDetailsInfo(movie)
//
//        val adapter = view.findViewById<RecyclerView>(R.id.rvActors).adapter as ActorListAdapter
//        adapter.setData(movie.actors)
//    }

    private fun updateMovieDetailsInfo(movie: Movie) {
        val bgImage = view?.findViewById<ImageView>(R.id.ivBgImage)
        val context = view?.context
        if (context != null && bgImage != null) {
            Glide
                .with(context.applicationContext)
                .load(movie.detailImageUrl)
                .into(bgImage)
        }

        view?.findViewById<TextView>(R.id.tvPG)?.text =
            context?.getString(R.string.movies_list_allowed_age_template, movie.pgAge)

        view?.findViewById<TextView>(R.id.tvMovieName)?.text = movie.title
        view?.findViewById<TextView>(R.id.tvGenre)?.text =
            movie.genres.joinToString { it.name }
        view?.findViewById<TextView>(R.id.tvReviewsCount)?.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        view?.findViewById<TextView>(R.id.tvStoryline)?.text = movie.storyLine

        val starsImages = listOf<ImageView?>(
            view?.findViewById(R.id.ivStarOne),
            view?.findViewById(R.id.ivStarTwo),
            view?.findViewById(R.id.ivStarThree),
            view?.findViewById(R.id.ivStarFour),
            view?.findViewById(R.id.ivStarFive)
        )
        starsImages.forEachIndexed { index, imageView ->
            imageView?.let {
                val colorId =
                    if (movie.rating > index) R.color.pink_light else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }
    }

    companion object {
        private const val PARAM_MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int) = MovieDetailsFragment().also {
            val args = bundleOf(
                PARAM_MOVIE_ID to movieId
            )
            it.arguments = args
        }
    }

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }


}