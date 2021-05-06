package com.example.moodtrakeranime

class Mood {
    var mood = 0
    var timestamp = 0L
    var latitude = 0.0
    var longitude = 0.0
    companion object{
        val GOOD = 0
        val NORM = 1
        val BAD = 2
    }
}