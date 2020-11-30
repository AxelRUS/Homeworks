package ru.ekhalikov.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MovieListFragment.OnCardClick,
    MovieDetailsFragment.BackButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance())
                .commit()
        }
    }

    override fun onClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance())
            .commit()
    }

    override fun onBackButtonPressed() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieListFragment.newInstance())
            .commit()
    }
}