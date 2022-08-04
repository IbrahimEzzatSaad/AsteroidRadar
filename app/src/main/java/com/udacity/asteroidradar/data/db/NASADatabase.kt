package com.udacity.asteroidradar.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AsteroidTable::class, DayAsteroidTable::class], version = 1, exportSchema = false)
abstract class NASADatabase : RoomDatabase() {

    /**
     * Connects the database to the DAO.
     */
    abstract val nasaDatabaseDao: NASADao


    /**
     * Define a companion object, this allows us to add functions on the NASADatabase class.
     *
     * For example, clients can call `NASADatabase.getInstance(context)` to instantiate
     * a new NASADatabase.
     */
    companion object {
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */
        @Volatile
        private var INSTANCE: NASADatabase? = null


        /**
         * Helper function to get the database.
         *
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         *
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         *
         * Singleton pattern
         *
         * @param context The application context Singleton, used to get access to the filesystem.
         */
        fun getInstance(context: Context): NASADatabase {
            // Only one thread may enter a synchronized block at a time.
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NASADatabase::class.java,
                        "nasa_history_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }


                return instance
            }

        }
    }

}