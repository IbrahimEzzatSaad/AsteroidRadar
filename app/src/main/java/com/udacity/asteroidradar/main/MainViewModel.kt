package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.udacity.asteroidradar.db.NASADatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.repository.NasaRepository
import com.udacity.asteroidradar.repository.RequestState
import kotlinx.coroutines.launch
import java.time.Duration

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = NASADatabase.getInstance(application)
    private val Repository = NasaRepository(database)

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
        refreshImageOfDay()
    }

    private fun refreshImageOfDay() = viewModelScope.launch {
        Repository.refreshAsteroids().apply {
            _state.value = this.first
            _pictureOfDay.value = this.third
            _list.value = this.second }
    }
}