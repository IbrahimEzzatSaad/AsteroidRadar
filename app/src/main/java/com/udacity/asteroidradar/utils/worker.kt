package com.udacity.asteroidradar.utils

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.data.db.NASADatabase
import com.udacity.asteroidradar.data.repository.NasaRepository
import retrofit2.HttpException

class FetchDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    /**
     * A coroutine-friendly method to do  work.
     */
    override suspend fun doWork(): Result {
        val database = NASADatabase.getInstance(applicationContext)

        val repository = NasaRepository(database)
        return try {
            repository.refreshAsteroids()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}