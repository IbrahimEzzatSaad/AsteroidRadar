package com.udacity.asteroidradar.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.data.db.AsteroidTable
import com.udacity.asteroidradar.data.db.DayAsteroidTable
import retrofit2.http.DELETE

@Dao
interface NASADao {

    @Query("SELECT * FROM asteroid_table ORDER BY closeApproachDate ASC")
    fun getAllAsteroid() : List<AsteroidTable>

    @Query("SELECT * FROM asteroid_table WHERE closeApproachDate >= :week ORDER by closeApproachDate ASC")
    fun getWeekAsteroids(week: String) : List<AsteroidTable>

    @Query("SELECT * FROM asteroid_table WHERE closeApproachDate == :day ORDER by closeApproachDate ASC")
    fun getDayAsteroids(day: String) : List<AsteroidTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAsteroid(vararg asteroid: AsteroidTable)


    /** ------------------------------------------ Table Separation ------------------------------------------ */


    @Query("SELECT * FROM day_asteroid_table")
    fun getTodayAsteroid() : DayAsteroidTable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodayAsteroid(vararg asteroid: DayAsteroidTable)

}