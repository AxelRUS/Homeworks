package ru.ekhalikov.homework2.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.ekhalikov.homework2.BuildConfig
import ru.ekhalikov.homework2.data.network.apis.MoviesApi

class MoviesNetworkModule(baseUrl: String) : MoviesNetworkClient {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val moviesClient = OkHttpClient.Builder()
        .addInterceptor(MoviesApiQueryInterceptor(hashMapOf("api_key" to BuildConfig.API_KEY)))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val moviesRetrofit = Retrofit.Builder()
        .client(moviesClient)
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val moviesApi = moviesRetrofit.create(MoviesApi::class.java)

    override fun moviesApi(): MoviesApi = moviesApi

}