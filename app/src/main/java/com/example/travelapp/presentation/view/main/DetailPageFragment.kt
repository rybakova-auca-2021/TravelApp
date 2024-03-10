package com.example.travelapp.presentation.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentDetailPageBinding
import com.example.travelapp.databinding.FragmentMainPageBinding
import com.example.travelapp.presentation.view.HomeActivity


class DetailPageFragment : Fragment() {
    private var _binding: FragmentDetailPageBinding? = null
    private val binding: FragmentDetailPageBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        (requireActivity() as HomeActivity).hideBtmNav()
        return binding.root
    }


}