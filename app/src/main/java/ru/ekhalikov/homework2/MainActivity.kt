package ru.ekhalikov.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity(), MovieListFragment.OnCardClick,
    MovieDetailsFragment.BackButtonListener {

    var container: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)


//        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance())
                .commit()
        }
    }

    override fun onClick() {
        supportFragmentManager.beginTransaction()
//                .addToBackStack(null)
            .replace(R.id.container, MovieDetailsFragment.newInstance())
            .commit()
    }

    override fun onBackButtonPressed() {
        supportFragmentManager.beginTransaction()
//            .addToBackStack(null)
            .replace(R.id.container, MovieListFragment.newInstance())
            .commit()
    }
}