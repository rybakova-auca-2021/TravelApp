package com.example.travelapp.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentPopularPageBinding


class PopularPageFragment : Fragment() {
    private var _binding: FragmentPopularPageBinding? = null
    private val binding: FragmentPopularPageBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}