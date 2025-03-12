package com.example.f1logs

data class Race(
    val id: Int,
    val name: String,
    val circuit: String,
    val date: String,
    var podium: String? = null
)
