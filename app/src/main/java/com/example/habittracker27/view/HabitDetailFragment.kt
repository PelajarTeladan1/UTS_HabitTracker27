package com.example.habittracker27.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker27.R
import com.example.habittracker27.databinding.FragmentHabitDetailBinding
import com.example.habittracker27.model.Habit
import com.example.habittracker27.viewmodel.ListViewModel

class HabitDetailFragment : Fragment() {

    private lateinit var binding: FragmentHabitDetailBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        setupToolbar()
        setupSpinner()
        setupButton()
    }

    private fun setupToolbar() {
        binding.toolbarDetail.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupSpinner() {
        val iconList = listOf(
            "Water",
            "Walking",
            "Sleep",
            "Reading",
            "Workout",
            "Meditation",
            "Study",
            "Food"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            iconList
        )

        binding.spinnerIcon.adapter = adapter
    }

    private fun setupButton() {
        binding.btnCreateHabit.setOnClickListener {
            createHabit()
        }
    }

    private fun createHabit() {
        val name = binding.txtHabitName.text.toString().trim()
        val desc = binding.txtDescription.text.toString().trim()
        val goalStr = binding.txtGoal.text.toString().trim()
        val unit = binding.txtUnit.text.toString().trim()
        val iconKey = binding.spinnerIcon.selectedItem.toString()

        if (name.isEmpty() || desc.isEmpty() || goalStr.isEmpty() || unit.isEmpty()) {
            Toast.makeText(context, "Semua field wajib diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        val goal = goalStr.toIntOrNull()
        if (goal == null || goal <= 0) {
            Toast.makeText(context, "Goal harus angka dan > 0!", Toast.LENGTH_SHORT).show()
            return
        }

        val currentList = viewModel.habitList.value
        val newId = if (currentList == null) 1 else currentList.size + 1

        val newHabit = Habit(
            id = newId,
            name = name,
            description = desc,
            goal = goal,
            progress = 0,
            unit = unit,
            icon = getIcon(iconKey)
        )

        viewModel.addHabit(newHabit)
        Toast.makeText(context, "Habit berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun getIcon(iconKey: String): Int {
        return when (iconKey) {
            "Water" -> R.drawable.baseline_water_drop_24
            "Walking" -> R.drawable.baseline_directions_walk_24
            "Sleep" -> R.drawable.baseline_bed_24
            "Reading" -> R.drawable.baseline_menu_book_24
            "Workout" -> R.drawable.baseline_fitness_center_24
            "Meditation" -> R.drawable.baseline_self_improvement_24
            "Study" -> R.drawable.baseline_school_24
            "Food" -> R.drawable.baseline_restaurant_24
            else -> R.drawable.ic_launcher_background
        }
    }
}