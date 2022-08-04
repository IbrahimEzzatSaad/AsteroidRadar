package com.udacity.asteroidradar.model

import com.udacity.asteroidradar.data.db.AsteroidTable
import com.udacity.asteroidradar.data.db.DayAsteroidTable

fun ArrayList<Asteroid>.asDatabaseModel(): Array<AsteroidTable> {
    return map {
        AsteroidTable(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous)
    }.toTypedArray()
}


fun List<AsteroidTable>.asDomainModel() : List<Asteroid>{
    return this.map{ Asteroid(
        id = it.id,
        codename = it.codename,
        closeApproachDate = it.closeApproachDate,
        absoluteMagnitude = it.absoluteMagnitude,
        estimatedDiameter = it.estimatedDiameter,
        relativeVelocity = it.relativeVelocity,
        distanceFromEarth = it.distanceFromEarth,
        isPotentiallyHazardous = it.isPotentiallyHazardous)
    }
}




fun PictureOfDay.asDatabaseModel() : DayAsteroidTable {
    return DayAsteroidTable(0,this.mediaType,this.title,this.url)
}


fun DayAsteroidTable?.asDomainModel() : PictureOfDay?{
    return this?.let { PictureOfDay(mediaType = it.mediaType, title = it.title, url = it.url) }

}