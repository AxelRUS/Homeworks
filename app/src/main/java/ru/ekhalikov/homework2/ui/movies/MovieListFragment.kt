package ru.ekhalikov.homework2.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.di.MovieRepositoryProvider

class MovieListFragment : Fragment() {

    var onCardClick: OnCardClick? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCardClick) {
            onCardClick = context
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
//        val cardView = view.findViewById(R.id.cvMovie) as CardView?
//        cardView?.setOnClickListener { onCardClick?.onClick() }
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(activity, 2)
        val adapter = MovieAdapter({ onCardClick?.onClick() })
        recycler.adapter = adapter
        loadDataToAdapter(adapter)
    }

    override fun onDetach() {
        super.onDetach()
        onCardClick = null
    }

    fun loadDataToAdapter(adapter: MovieAdapter) {
        val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
        lifecycleScope.launch {
            val movies = repository.loadMovies()
            adapter.setData(movies)
        }
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }

    interface OnCardClick {
        fun onClick()
    }
}