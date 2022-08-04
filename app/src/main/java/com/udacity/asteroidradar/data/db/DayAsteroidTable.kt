package com.udacity.asteroidradar.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_asteroid_table")
data class DayAsteroidTable(
    @PrimaryKey
    val id: Long = 0,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String)



