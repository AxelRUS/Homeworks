package ru.ekhalikov.homework2.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.appComponent
import ru.ekhalikov.homework2.model.Movie

class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel

    private var listener: MovieListItemClickListener? = null
    private val movieListAdapter = MovieListAdapter({ item ->
        listener?.onMovieSelected(item)
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieListItemClickListener) {
            listener = context
        }

        if (context is AppCompatActivity) {
            context.supportActionBar?.apply {
                title = getString(R.string.movies_list)
                setHomeAsUpIndicator(R.drawable.ic_actionbar_list)
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            this.layoutManager = GridLayoutManager(activity, 2)
            this.adapter = this@MovieListFragment.movieListAdapter
            setupViewModel()
        }
    }

    override fun onDetach() {
        // recycler = null, MAT & DDMS
        listener = null
        super.onDetach()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, appComponent().viewModelFactory())
            .get(MovieListViewModel::class.java)
        viewModel.movies.observe(
            this@MovieListFragment.viewLifecycleOwner,
            this@MovieListFragment::updateAdapter
        )
        viewModel.onViewCreated()
    }

    private fun updateAdapter(movies: List<Movie>) {
//        val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
        lifecycleScope.launch {
//            val movies = repository.loadMovies()
            movieListAdapter.setData(movies)
        }
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }

    interface MovieListItemClickListener {
        fun onMovieSelected(movie: Movie)
    }
}