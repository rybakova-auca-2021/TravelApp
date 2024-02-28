package com.example.travelapp.presentation.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView.FindListener
import androidx.navigation.fragment.findNavController
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.btnSeeAllPopular.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment2_to_popularPageFragment)
        }
        binding.btnAllMustVisitPlaces.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment2_to_mustVisitPageFragment)
        }
        binding.btnAllPackages.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment2_to_packagesPageFragment)
        }
    }
}