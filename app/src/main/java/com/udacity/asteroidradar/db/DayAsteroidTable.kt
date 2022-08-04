package com.udacity.asteroidradar.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.PictureOfDay
import java.util.*

@Entity(tableName = "day_asteroid_table")
data class DayAsteroidTable(
    @PrimaryKey
    val id: Long = 0,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String)



