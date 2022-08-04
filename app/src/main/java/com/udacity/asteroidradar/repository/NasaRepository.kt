package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.db.AsteroidTable
import com.udacity.asteroidradar.db.NASADatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.model.asDatabaseModel
import com.udacity.asteroidradar.model.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NasaRepository(private val database: NASADatabase) {

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the videos for use, observe [videos]
     */
    suspend fun refreshAsteroids() : Triple<RequestState, List<Asteroid>, PictureOfDay?>  = withContext(Dispatchers.IO) {

        try {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            val asteroids =  Network.apiService.getAsteroids(current.format(formatter).toString(), current.plusDays(7).format(formatter).toString())
            val asteroidOfTheDay =  Network.apiService.getAsteroidOfTheDay()
            val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroids))


            database.nasaDatabaseDao.insertAllAsteroid(*parsedAsteroids.asDatabaseModel())
            database.nasaDatabaseDao.insertTodayAsteroid(asteroidOfTheDay.asDatabaseModel())



            Triple(RequestState.DONE , database.nasaDatabaseDao.getAllAsteroid().asDomainModel(), database.nasaDatabaseDao.getTodayAsteroid().asDomainModel())

        }catch (e : Exception){
            Triple(RequestState.FAILED , database.nasaDatabaseDao.getAllAsteroid().asDomainModel(), database.nasaDatabaseDao.getTodayAsteroid().asDomainModel())
        }
    }



}