package com.example.movies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.model.MovieList
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListAdapter(val movieList:ArrayList<Movie>):
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    class MovieListViewHolder(var view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie_list,parent,false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
       //holder.view.vote_average_textview.text = movieList[position].voteAverage.toString()
       holder.view.realease_date_textview.text = movieList[position].realeaseDate
       holder.view.title_textview.text = movieList[position].orginalTitle

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovieList(newMovieList:List<Movie>){
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

}