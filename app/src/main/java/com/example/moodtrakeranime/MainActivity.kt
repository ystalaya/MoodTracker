package com.example.moodtrakeranime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main. activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //вызвать при нажатии фунцию
        mood_bad.setOnClickListener{registerMood(it)}
        mood_norm.setOnClickListener{registerMood(it)}
        mood_good.setOnClickListener{registerMood(it)}
    }

    fun registerMood(view: View) {
        val mood = Mood()
        when (view) {
            mood_bad -> mood.mood = Mood.BAD
            mood_norm -> mood.mood = Mood.NORM
            mood_good -> mood.mood = Mood.GOOD

        }
        mood.timestamp = System.currentTimeMillis() / 1000
        val dbManager= DBManager.getInstance(this)
        dbManager!!.insert(mood)
        Toast.makeText(this,"Записей в базе: " + dbManager.moods(), Toast.LENGTH_LONG).show()
        startActivity(Intent(this,StatActivity::class.java))
         }
}