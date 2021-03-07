package ru.ekhalikov.homework2.ui.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.di.MovieRepositoryProvider
import ru.ekhalikov.homework2.model.Movie

class MovieDetailsFragment : Fragment() {

    var listener: MovieDetailsBackClickListener? = null

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

        val movieId = arguments?.getSerializable(PARAM_MOVIE_ID) as? Int ?: return

        val actorRecycler = view.findViewById<RecyclerView>(R.id.rvActors)
        actorRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val actorAdapter = ActorListAdapter()
        actorRecycler.adapter = actorAdapter

        lifecycleScope.launch {
            val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
            val movie = repository.loadMovie(movieId)

            if (movie != null) {
                bindUI(view, movie)
            } else {
                showMovieNotFoundError()
            }
        }
    }

    fun loadDataToAdapter(adapter: ActorListAdapter) {
        val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()

    }

    fun bindUI(view: View, movie: Movie){

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