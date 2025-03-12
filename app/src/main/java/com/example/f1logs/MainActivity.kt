package com.example.f1logs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val raceList = listOf(
            Race(1, "Bahrain Grand Prix", "Bahrain International Circuit", "March 2"),
            Race(2, "Saudi Arabian Grand Prix", "Jeddah Corniche Circuit", "March 9"),
            Race(3, "Australian Grand Prix", "Albert Park Circuit", "March 24"),
            Race(4, "Japanese Grand Prix", "Suzuka Circuit", "April 7"),
            Race(5, "Chinese Grand Prix", "Shanghai International Circuit", "April 21")
        )

        adapter = RaceAdapter(this, raceList)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
