package com.udacity.asteroidradar.domain

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.data.db.NASADatabase
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.data.repository.NasaRepository
import com.udacity.asteroidradar.utils.AsteroidsByDate
import com.udacity.asteroidradar.utils.RequestState
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = NASADatabase.getInstance(application)
    private val repository = NasaRepository(database)

    private var _list =  MutableLiveData<List<Asteroid>>()
    val list : LiveData<List<Asteroid>>
        get() {
          return  _list
        }

    private var _pictureOfDay =  MutableLiveData<PictureOfDay>()
    val pictureOfDay : LiveData<PictureOfDay>
        get() {
            return  _pictureOfDay
        }

    private var _state =  MutableLiveData<RequestState>()
    val state : LiveData<RequestState>
        get() {
            return  _state
        }

    init {
        _state.value = RequestState.LOADING
        refreshAsteroids()
    }

    private fun refreshAsteroids() = viewModelScope.launch {
        repository.refreshAsteroids().apply {
            _state.value = this.first
            _pictureOfDay.value = this.third
            _list.value = this.second }

    }

    fun getAsteroidsByDate(date : AsteroidsByDate)  = viewModelScope.launch {
        when (date) {
            AsteroidsByDate.WEEK -> _list.value = repository.getWeekAsteroids()
            AsteroidsByDate.DAY -> _list.value = repository.getDayAsteroids()
            else -> _list.value = repository.getAllAsteroids()
        }
    }



}