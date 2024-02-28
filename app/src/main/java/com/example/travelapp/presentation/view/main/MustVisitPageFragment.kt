package com.example.travelapp.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelapp.databinding.FragmentMustVisitPageBinding

class MustVisitPageFragment : Fragment() {
    private var _binding: FragmentMustVisitPageBinding? = null
    private val binding: FragmentMustVisitPageBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMustVisitPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}