package com.udacity.asteroidradar.data.repository

import com.udacity.asteroidradar.data.api.Network
import com.udacity.asteroidradar.data.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.db.NASADatabase
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.model.asDatabaseModel
import com.udacity.asteroidradar.model.asDomainModel
import com.udacity.asteroidradar.utils.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NasaRepository(private val database: NASADatabase) {

    val current = LocalDateTime.now()

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
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            val asteroids =  Network.apiService.getAsteroids(current.format(formatter).toString(), current.plusDays(7).format(formatter).toString())
            val asteroidOfTheDay =  Network.apiService.getAsteroidOfTheDay()
            val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroids))


            database.clearAllTables()
            database.nasaDatabaseDao.insertAllAsteroid(*parsedAsteroids.asDatabaseModel())
            database.nasaDatabaseDao.insertTodayAsteroid(asteroidOfTheDay.asDatabaseModel())



            Triple(RequestState.DONE, getAllAsteroids(), getPictureOfDay())

        }catch (e : Exception){
            Triple(RequestState.FAILED, getAllAsteroids(), getPictureOfDay())
        }
    }

    suspend fun getPictureOfDay() : PictureOfDay?   = withContext(Dispatchers.IO){
        database.nasaDatabaseDao.getTodayAsteroid().asDomainModel()
    }

    suspend fun getWeekAsteroids() : List<Asteroid>  = withContext(Dispatchers.IO){
         database.nasaDatabaseDao.getWeekAsteroids(current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).asDomainModel()
    }

    suspend fun getDayAsteroids() : List<Asteroid>   = withContext(Dispatchers.IO){
         database.nasaDatabaseDao.getDayAsteroids(current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).asDomainModel()
    }

    suspend fun getAllAsteroids() : List<Asteroid>   = withContext(Dispatchers.IO){
        database.nasaDatabaseDao.getAllAsteroid().asDomainModel()
    }



}