package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY

import com.udacity.asteroidradar.Constants.START_DATE
import com.udacity.asteroidradar.Constants.END_DATE
import com.udacity.asteroidradar.Constants.KEY
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A retrofit service to fetch a devbyte playlist.
 */
interface EndPoint {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query(START_DATE) startDate: String, // Date Format -> YYYY-MM-DD
        @Query(END_DATE) endDate: String, // Date Format -> YYYY-MM-DD
        @Query(API_KEY) apiKey: String = KEY
    ): String


    @GET("planetary/apod")
    suspend fun getAsteroidOfTheDay(
        @Query(API_KEY) apiKey: String = KEY
    ): PictureOfDay


}
