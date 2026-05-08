package com.example.habittracker27.model

import java.io.Serializable

data class Habit (
    val id: Int,
    var name: String,
    var description: String,
    var goal: Int,
    var progress: Int,
    var unit: String,
    var icon: Int
) : Serializable
