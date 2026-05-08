package com.example.habittracker27.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittracker27.R
import com.example.habittracker27.model.Habit

class ListViewModel : ViewModel(){

    val habitList = MutableLiveData<ArrayList<Habit>>()

    fun refresh() {
        if (habitList.value == null) {
            val list = arrayListOf(
                Habit(1, "Minum Air", "Minum 2 Liter per Hari", 8, 3, "gelas", R.drawable.baseline_water_drop_24),
                Habit(2, "Jalan Kaki", "10k Langkah per Hari", 10, 5, "km", R.drawable.baseline_directions_walk_24),
                Habit(3, "Tidur", "Minimal 6 jam", 8, 6, "jam", R.drawable.baseline_bed_24),
                Habit(4, "Membaca", "Baca buku setiap hari", 20, 5, "halaman", R.drawable.baseline_menu_book_24),
                Habit(5, "Olahraga", "Workout ringan", 5, 2, "kali", R.drawable.baseline_fitness_center_24),
                Habit(6, "Meditasi", "Tenangkan pikiran", 10, 4, "menit", R.drawable.baseline_self_improvement_24)
            )
            habitList.value = list
        }
    }

    fun increaseProgress(habit: Habit) {
        if (habit.progress < habit.goal) {
            habit.progress++
            habitList.value = ArrayList(habitList.value!!)
        }
    }

    fun decreaseProgress(habit: Habit) {
        if (habit.progress > 0) {
            habit.progress--
            habitList.value = ArrayList(habitList.value!!)
        }
    }

    fun addHabit(habit: Habit) {
        val currentList = habitList.value ?: arrayListOf()
        val newList = ArrayList(currentList)
        newList.add(habit)
        habitList.value = newList
    }
}