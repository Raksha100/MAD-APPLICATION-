package com.example.f1logs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RaceDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_race_details)

        // Get race details from intent
        val raceName = intent.getStringExtra("race_name") ?: "Unknown Race"
        val circuit = intent.getStringExtra("circuit") ?: "Unknown Circuit"
        val trackLength = intent.getStringExtra("track_length") ?: "N/A"
        val date = intent.getStringExtra("date") ?: "N/A"
        val day = intent.getStringExtra("day") ?: "N/A"
        val location = intent.getStringExtra("location") ?: "N/A"

        // Find Views
        val raceTitle: TextView = findViewById(R.id.raceTitle)
        val circuitName: TextView = findViewById(R.id.circuitName)
        val trackInfo: TextView = findViewById(R.id.trackInfo)
        val locationInfo: TextView = findViewById(R.id.locationInfo)
        val podiumButton: Button = findViewById(R.id.podiumButton)

        // Set Text
        raceTitle.text = raceName
        circuitName.text = circuit
        trackInfo.text = "$trackLength | $date, $day"
        locationInfo.text = location

        // Open Podium Selection Page
        podiumButton.setOnClickListener {
            val intent = Intent(this, PodiumActivity::class.java)
            intent.putExtra("race_name", raceName)
            startActivity(intent)
        }
    }
}
