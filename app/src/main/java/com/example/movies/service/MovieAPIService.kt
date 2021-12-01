package com.example.movies.service

import com.example.movies.model.MovieList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import com.google.gson.Gson




class MovieAPIService {

    private val BASE_URL = "https://developers.themoviedb.org/3/"
    private val API_KEY = "45e72af51ad7bb107f12e61387040e94"

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getMovieList(page: Int): Single<MovieList> {
        return api.getMovieList(apiKey = API_KEY, page = page)
    }
}