package com.udacity.asteroidradar.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.db.DayAsteroidTable


@JsonClass(generateAdapter = true)
data class AsteroidDay(val asteroid: ImageOfDayResponse)


@JsonClass(generateAdapter = true)
data class ImageOfDayResponse(
    val url: String,
    @Json(name = "media_type")
    val mediaType: String,
    val title: String, )






@JsonClass(generateAdapter = true)
data class NASAAsteroids(
    @Json(name = "near_earth_objects")
    val AsteroidDays : List<AsteroidsDay>
)

@JsonClass(generateAdapter = true)
data class AsteroidsDay(
    val asteroid : List<Asteroid>
)

@JsonClass(generateAdapter = true)
data class Asteroid(
    val id : Long,
    val name : String,
    @Json(name = "absolute_magnitude_h")
    val AbsoluteMagnitude : Double,
    @Json(name = "estimated_diameter")
    val EstimatedDiameter : EstimatedDiameters,
    @Json(name = "is_potentially_hazardous_asteroid")
    val Hazardous : Boolean,
    @Json(name = "close_approach_data")
    val CloseApproach : Approach
)

@JsonClass(generateAdapter = true)
data class EstimatedDiameters(
    @Json(name = "kilometers")
    val Kilometer : Kilometer
)

@JsonClass(generateAdapter = true)
data class Kilometer(
    @Json(name = "estimated_diameter_min")
    val estimatedMin : Double,
    @Json(name = "estimated_diameter_max")
    val estimatedMax : Double
)


@JsonClass(generateAdapter = true)
data class Approach(
    @Json(name = "close_approach_date")
    val closeApproachDate: String,
    @Json(name = "miss_distance")
    val missDistance: MissDistance,
    @Json(name = "relative_velocity")
    val relativeVelocity: RelativeVelocity,
)


@JsonClass(generateAdapter = true)
data class MissDistance(
    val astronomical: String,
)

@JsonClass(generateAdapter = true)
data class RelativeVelocity(
    val kilometers_per_second: String,
)


