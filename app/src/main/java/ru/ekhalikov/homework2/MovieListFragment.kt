package ru.ekhalikov.homework2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

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
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cardView = view.findViewById(R.id.cvMovie) as CardView?
        cardView?.setOnClickListener { onCardClick?.onClick() }
    }

    override fun onDetach() {
        super.onDetach()
        onCardClick = null
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }

    interface OnCardClick {
        fun onClick()
    }
}