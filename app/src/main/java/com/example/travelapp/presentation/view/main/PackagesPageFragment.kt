package com.example.travelapp.presentation.view.main

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentPackagesPageBinding
import com.example.travelapp.presentation.view.HomeActivity
import com.example.travelapp.presentation.viewModel.main.GetMustVisitListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PackagesPageFragment : Fragment() {
    private var _binding: FragmentPackagesPageBinding? = null
    private val binding: FragmentPackagesPageBinding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainPackagesAdapter
    private val viewModel: GetMustVisitListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPackagesPageBinding.inflate(inflater, container, false)
        recyclerView = binding.rvPackages
        (requireActivity() as HomeActivity).hideBtmNav()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupAdapter()
        fetchData()
    }

    private fun setupAdapter() {
        adapter = MainPackagesAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun fetchData() {
        viewModel.getMustVisitPlaces()
        viewModel.result.observe(viewLifecycleOwner, Observer {
                result -> result.onSuccess { items ->
                adapter.updateData(items)
            }.onFailure {
                Log.e(ContentValues.TAG, "Error fetching packages")
            }
        })
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_packagesPageFragment_to_mainPageFragment2)
        }
    }
}