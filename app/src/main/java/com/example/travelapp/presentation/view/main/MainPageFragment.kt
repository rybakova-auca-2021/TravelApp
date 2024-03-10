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
import com.example.travelapp.databinding.FragmentMainPageBinding
import com.example.travelapp.presentation.view.HomeActivity
import com.example.travelapp.presentation.viewModel.main.GetMustVisitListViewModel
import com.example.travelapp.presentation.viewModel.main.GetPackagesViewModel
import com.example.travelapp.presentation.viewModel.main.GetPopularListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding get() = _binding!!
    private lateinit var rvPopular: RecyclerView
    private lateinit var rvMustVisit: RecyclerView
    private lateinit var rvPackages: RecyclerView
    private lateinit var popularItemsAdapter: PopularItemsAdapter
    private lateinit var mustVisitItemsAdapter: MustVisitPlacesAdapter
    private lateinit var packageAdapter: PackagesAdapter
    private val getPopularListViewModel: GetPopularListViewModel by viewModel()
    private val getMustVisitListViewModel: GetMustVisitListViewModel by viewModel()
    private val getPackagesViewModel: GetPackagesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        (requireActivity() as HomeActivity).showBtmNav()
        rvPopular = binding.rvPopular
        rvMustVisit = binding.rvMustVisit
        rvPackages = binding.rvPackages
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupAdapters()
        fetchPopularPlaces()
        fetchMustVisitPlaces()
        fetchPackagesPlaces()
    }

    private fun setupAdapters() {
        popularItemsAdapter = PopularItemsAdapter(emptyList())
        rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvPopular.adapter = popularItemsAdapter

        mustVisitItemsAdapter = MustVisitPlacesAdapter(emptyList())
        rvMustVisit.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMustVisit.adapter = mustVisitItemsAdapter

        packageAdapter = PackagesAdapter(emptyList())

        rvPackages.layoutManager = GridLayoutManager(requireContext(), 2)
        rvPackages.adapter = packageAdapter
    }

    private fun fetchPopularPlaces() {
        getPopularListViewModel.getPopularPlaces()
        getPopularListViewModel.result.observe(viewLifecycleOwner, Observer {
            result -> result.onSuccess { popularItems ->
                popularItemsAdapter.updateData(popularItems)
            }.onFailure { error ->
                Log.e(ContentValues.TAG, "Error fetching popular places: $error")
            }
        })
    }

    private fun fetchMustVisitPlaces() {
        getMustVisitListViewModel.getMustVisitPlaces()
        getMustVisitListViewModel.result.observe(viewLifecycleOwner, Observer {
            result -> result.onSuccess { popularItems ->
                mustVisitItemsAdapter.updateData(popularItems)
            }.onFailure { error ->
                Log.e(ContentValues.TAG, "Error fetching must visit places: $error")
            }
        })
    }

    private fun fetchPackagesPlaces() {
        getPackagesViewModel.getPackages()
        getPackagesViewModel.result.observe(viewLifecycleOwner, Observer {
            result -> result.onSuccess { items ->
                packageAdapter.updateData(items)
            }.onFailure { error ->
                Log.e(ContentValues.TAG, "Error fetching packages: $error")
            }
        })
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