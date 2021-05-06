package com.example.moodtrakeranime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_stat.*

class StatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)
        showStat();
    }

    fun showStat(){
        val dbManager = DBManager.getInstance(context = this)
        val bad = dbManager.moods(Mood.BAD)
        val norm = dbManager.moods(Mood.NORM)
        val good = dbManager.moods(Mood.GOOD)
        badPercent.append("" + bad)
        goodPercent.append("" + good)
        normPercent.append("" + norm)
    }
}