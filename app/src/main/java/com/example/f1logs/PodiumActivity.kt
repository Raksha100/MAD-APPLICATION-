package com.example.f1logs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PodiumActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var driverList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podium)

        sharedPreferences = getSharedPreferences("PodiumPrefs", Context.MODE_PRIVATE)

        val raceId = intent.getIntExtra("raceId", -1)

        driverList = arrayOf("Select Driver", "Verstappen", "Hamilton", "Leclerc", "Norris", "Russell", "Sainz", "Perez", "Alonso")

        val spinnerP1: Spinner = findViewById(R.id.spinnerP1)
        val spinnerP2: Spinner = findViewById(R.id.spinnerP2)
        val spinnerP3: Spinner = findViewById(R.id.spinnerP3)
        val saveButton: Button = findViewById(R.id.savePodiumButton)

        setupSpinner(spinnerP1)
        setupSpinner(spinnerP2)
        setupSpinner(spinnerP3)

        // Load saved podium data
        val savedPodium = sharedPreferences.getString("podium_$raceId", null)
        savedPodium?.let {
            val positions = it.split(", ")
            if (positions.size == 3) {
                spinnerP1.setSelection(driverList.indexOf(positions[0]))
                spinnerP2.setSelection(driverList.indexOf(positions[1]))
                spinnerP3.setSelection(driverList.indexOf(positions[2]))
            }
        }

        saveButton.setOnClickListener {
            val p1 = spinnerP1.selectedItem.toString()
            val p2 = spinnerP2.selectedItem.toString()
            val p3 = spinnerP3.selectedItem.toString()

            if (p1 == "Select Driver" || p2 == "Select Driver" || p3 == "Select Driver") {
                Toast.makeText(this, "Please select all podium positions!", Toast.LENGTH_SHORT).show()
            } else {
                val podiumText = "$p1, $p2, $p3"
                sharedPreferences.edit().putString("podium_$raceId", podiumText).apply()
                finish()
            }
        }
    }

    private fun setupSpinner(spinner: Spinner) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, driverList)
        spinner.adapter = adapter
    }
}
