package com.example.habittracker27.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker27.databinding.FragmentHabitListBinding
import com.example.habittracker27.model.HabitListAdapter
import com.example.habittracker27.viewmodel.ListViewModel

class HabitListFragment : Fragment() {

    private lateinit var binding: FragmentHabitListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        adapter = HabitListAdapter(arrayListOf(), viewModel)

        if (viewModel.habitList.value == null) {
            viewModel.refresh()
        }

        binding.recyclerHabit.layoutManager = LinearLayoutManager(context)
        binding.recyclerHabit.adapter = adapter

        binding.fabAddHabit.setOnClickListener {
            val action = HabitListFragmentDirections
                .actionHabitDetailFragment()

            it.findNavController().navigate(action)
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.habitList.observe(viewLifecycleOwner, Observer {
            adapter.updateHabitList(it)
        })
    }
}