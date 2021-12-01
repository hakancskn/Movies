package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.Movie
import com.example.movies.model.MovieList
import com.example.movies.service.MovieAPIService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import io.reactivex.android.schedulers.AndroidSchedulers

class MovieListViewModel : ViewModel() {

    var job: Job? = null
    var page:Int? = 0
    var maxPage:Int? = null
    val movieList = MutableLiveData<List<Movie>>()
    val movieListError = MutableLiveData<Boolean>()
    val movieListLoading = MutableLiveData<Boolean>()
    val listEnd = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val movieApiService = MovieAPIService()

    fun getDataFromApi() {


        movieListLoading.value = true

            if(page != null && maxPage != null && maxPage!!<= page!!){
                listEnd.postValue(true)
            }else{
                page = page?.plus(1)
                disposable.add(
                    movieApiService.getMovieList(page= page!!)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<MovieList>(){
                            override fun onSuccess(t: MovieList) {
                                //storeInSQLite(t)
                               // Toast.makeText(getApplication(),"Countries From API", Toast.LENGTH_LONG).show()
                                movieList.value = t.results
                                maxPage = t.totalPage!!
                                movieListLoading.value = false
                            }

                            override fun onError(e: Throwable) {

                                movieListLoading.value = false
                                movieListError.value = true
                                e.printStackTrace()
                            }

                        })
                )
            }






    }

}