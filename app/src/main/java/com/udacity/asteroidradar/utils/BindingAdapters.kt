package com.udacity.asteroidradar.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.model.PictureOfDay


@BindingAdapter("imageDay")
fun bindImageOfDay(imageView: ImageView, Picture: PictureOfDay?) {
    imageView.contentDescription = R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet.toString()
    Picture?.let { Picasso.get().load(Picture.url).into(imageView)
        imageView.contentDescription = String.format(R.string.nasa_picture_of_day_content_description_format.toString(), Picture.title)
    }

}


@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.contentDescription = "Its a Hazardous"
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.contentDescription = "Its not a Hazardous"
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.contentDescription = "Its a Hazardous"
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.contentDescription = "Its not a Hazardous"
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
