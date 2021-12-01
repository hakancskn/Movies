package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.view.adapter.MovieListAdapter
import com.example.movies.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {

    val movieListViewModel: MovieListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movielist.layoutManager = LinearLayoutManager(requireContext())
        observeLiveData()
    }

    private fun observeLiveData(){
        movieListViewModel.getDataFromApi()
        movieListViewModel.movieList.observe(viewLifecycleOwner) {
            val adapter = MovieListAdapter(ArrayList(it))
            movielist.adapter = adapter
        }

        movieListViewModel.movieListError.observe(viewLifecycleOwner){
            if(it){
                error_message_text_view.visibility = View.VISIBLE
            }else{
                error_message_text_view.visibility = View.GONE
            }
        }

        movieListViewModel.movieListLoading.observe(viewLifecycleOwner){
            if(it){
                progress_bar.visibility = View.VISIBLE
                error_message_text_view.visibility= View.GONE
            }else{
                progress_bar.visibility = View.GONE
            }
        }
    }

}