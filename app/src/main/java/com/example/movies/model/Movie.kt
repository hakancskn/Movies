package com.example.movies.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class Movie(
    @SerializedName("poster_path")
    val posterPath:String?,
    @SerializedName("release_date")
    val realeaseDate:String?,
    @SerializedName("id")
    val id:Int,
    @SerializedName("original_title")
    val orginalTitle:String?,
   /* @SerializedName("vote_average")
    val voteAverage:Float?*/
): Serializable
