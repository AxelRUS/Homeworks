package ru.ekhalikov.homework2.network

import okhttp3.Interceptor
import okhttp3.Response

class MoviesApiQueryInterceptor(private val args: HashMap<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request();
        
        val url = originalRequest.url.newBuilder().apply {
            args.forEach{ (key, value) -> addQueryParameter(key, value) }
        }.build()

        return chain.proceed(originalRequest.newBuilder()
            .url(url)
            .build()
        )
    }
}