package com.example.travelapp.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentWelcomePageBinding

class WelcomePageFragment : Fragment() {
    private lateinit var binding: FragmentWelcomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }


    private fun setupNavigation() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomePageFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_welcomePageFragment_to_signUpFragment)
        }
    }
}