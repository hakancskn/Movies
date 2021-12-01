package com.example.movies.service

import com.example.movies.model.MovieList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("discover/movie")
    fun getMovieList(
        @Query("api_key") apiKey:String = "",
        @Query("page") page: Int? = 1
    ): Single<MovieList>
}