# Introduction
This project uses NASA API to view near asteroids and their dangerous potential.

The project is a sample to implement and practice on the following:

 - Data binding
 - MVVM
 - Retrofit
 - Room
 - Caching
 - Picasso
 - WorkManager
 - TalkBack

The project is part of Udacity Nanodegree for Android development.


## Architecture

The project is separated to 3 layers

 - UI: this package contain only the UI files(Fragments, Activities, Application)
 - Domain: this package contain ViewModel, it serve as a middle layer between UI and Data
 - Data: this package contains the repository along with two data sources(Room, API)
 - Model: this package contains data classes and type converter
![enter image description here](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png)
