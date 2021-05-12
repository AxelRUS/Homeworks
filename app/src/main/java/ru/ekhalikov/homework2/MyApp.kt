package ru.ekhalikov.homework2

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.ekhalikov.homework2.data.JsonMovieRepository
import ru.ekhalikov.homework2.data.MovieRepository

interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}

interface ViewModelFactoryProvider {
    fun viewModelFactory(): ViewModelFactory
}

class MyApp : Application(), MovieRepositoryProvider, ViewModelFactoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)
    private lateinit var viewModelFactory: ViewModelFactory


    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository
    override fun viewModelFactory(): ViewModelFactory = viewModelFactory

    override fun onCreate() {
        super.onCreate()

        viewModelFactory = ViewModelFactory(JsonMovieRepository(this))
    }
}

fun Context.appComponent() = (applicationContext as MyApp)
fun Fragment.appComponent() = requireContext().appComponent()