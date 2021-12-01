package com.example.movies.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieList(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Movie>?,
    @SerializedName("total_pages")
    val totalPage: Int?
) : Serializable
