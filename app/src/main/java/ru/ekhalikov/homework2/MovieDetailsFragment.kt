package ru.ekhalikov.homework2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MovieDetailsFragment : Fragment() {

    var backButtonListener: BackButtonListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BackButtonListener) {
            backButtonListener = context
        }

        if (context is AppCompatActivity) {
            context.supportActionBar?.title = getString(R.string.movie_details)
//            context.supportActionBar?.setIcon(R.drawable.ic_actionbar_back)
            context.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_chevron_left)
            context.supportActionBar?.setHomeButtonEnabled(true)
            context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backButtonListener?.onBackButtonPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDetach() {
        super.onDetach()
        backButtonListener = null
    }

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    interface BackButtonListener {
        fun onBackButtonPressed()
    }


}