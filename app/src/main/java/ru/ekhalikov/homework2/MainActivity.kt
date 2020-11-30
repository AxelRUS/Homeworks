package ru.ekhalikov.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity(), MovieListFragment.OnCardClick {

    var container: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)

        container = findViewById(R.id.container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieListFragment.newInstance())
                    .commit()
        }
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onClick() {
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, MovieDetailsFragment.newInstance())
                .commit()
    }
}