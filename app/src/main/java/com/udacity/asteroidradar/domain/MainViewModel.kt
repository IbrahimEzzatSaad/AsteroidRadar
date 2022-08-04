package com.udacity.asteroidradar.domain

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.data.db.NASADatabase
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.data.repository.NasaRepository
import com.udacity.asteroidradar.utils.RequestState
import kotlinx.coroutines.launch

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