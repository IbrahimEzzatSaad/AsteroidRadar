package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay

@Dao
interface NASADao {

    @Query("SELECT * FROM asteroid_table ORDER BY closeApproachDate ASC")
    fun getAllAsteroid() : List<AsteroidTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAsteroid(vararg asteroid: AsteroidTable)



    @Query("SELECT * FROM day_asteroid_table")
    fun getTodayAsteroid() : DayAsteroidTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodayAsteroid(vararg asteroid: DayAsteroidTable)

}