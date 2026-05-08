package com.example.habittracker27.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.habittracker27.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnLogin.setOnClickListener {
            val username = binding.txtName.text.toString()
            val password = binding.txtPassword.text.toString()

            if(username == "student" && password == "123"){
                val action = LoginFragmentDirections.actionHabitListFragment()
                view.findNavController().navigate(action)
            } else {
                binding.txtError.visibility = View.VISIBLE
            }
        }
    }
}