package com.example.f1logs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class RaceAdapter(private val context: Context, private val races: List<Race>) :
    RecyclerView.Adapter<RaceAdapter.RaceViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("PodiumPrefs", Context.MODE_PRIVATE)

    private val raceList = races.sortedWith(compareBy<Race> { it.date.toFormattedDate().before(Date()) }
        .thenBy { it.date.toFormattedDate() }).toMutableList()

    class RaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val raceTitle: TextView = view.findViewById(R.id.raceTitle)
        val circuitName: TextView = view.findViewById(R.id.circuitName)
        val raceDate: TextView = view.findViewById(R.id.raceDate)
        val podiumResults: TextView = view.findViewById(R.id.podiumResults)
        val setPodiumButton: Button = view.findViewById(R.id.setPodiumButton)
        val editPodiumButton: Button = view.findViewById(R.id.editPodiumButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_race, parent, false)
        return RaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: RaceViewHolder, position: Int) {
        val race = raceList[position]

        holder.raceTitle.text = race.name
        holder.circuitName.text = race.circuit
        holder.raceDate.text = race.date

        // Load saved podium data
        val savedPodium = sharedPreferences.getString("podium_${race.id}", null)
        race.podium = savedPodium

        if (race.podium != null) {
            holder.podiumResults.text = "🏆 $savedPodium"
            holder.podiumResults.visibility = View.VISIBLE
            holder.setPodiumButton.visibility = View.GONE
            holder.editPodiumButton.visibility = View.VISIBLE
        } else {
            holder.podiumResults.visibility = View.GONE
            holder.setPodiumButton.visibility = View.VISIBLE
            holder.editPodiumButton.visibility = View.GONE
        }

        val backgroundColor = if (position % 2 == 0) R.color.teal_forest else R.color.vanilla_latte
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, backgroundColor))

        holder.setPodiumButton.setOnClickListener {
            val intent = Intent(context, PodiumActivity::class.java)
            intent.putExtra("raceId", race.id)
            context.startActivity(intent)
        }

        holder.editPodiumButton.setOnClickListener {
            val intent = Intent(context, PodiumActivity::class.java)
            intent.putExtra("raceId", race.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = raceList.size
}

fun String.toFormattedDate(): Date {
    val format = SimpleDateFormat("MMMM d", Locale.US)
    return format.parse(this) ?: Date()
}
