package com.example.travelapp.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelapp.databinding.FragmentPackagesPageBinding


class PackagesPageFragment : Fragment() {
    private var _binding: FragmentPackagesPageBinding? = null
    private val binding: FragmentPackagesPageBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPackagesPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}