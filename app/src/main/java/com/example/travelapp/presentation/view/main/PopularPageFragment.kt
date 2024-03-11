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
import com.example.travelapp.data.model.PlaceModel
import com.example.travelapp.databinding.FragmentPopularPageBinding
import com.example.travelapp.presentation.view.HomeActivity
import com.example.travelapp.presentation.viewModel.main.GetPopularListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PopularPageFragment : Fragment() {
    private var _binding: FragmentPopularPageBinding? = null
    private val binding: FragmentPopularPageBinding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlacesAdapter
    private val viewModel: GetPopularListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularPageBinding.inflate(inflater, container, false)
        recyclerView = binding.rvPopular
        (requireActivity() as HomeActivity).hideBtmNav()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupAdapter()
        fetchData()
        setupAdapterClicks()
    }

    private fun setupAdapter() {
        adapter = PlacesAdapter(emptyList())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
    }

    private fun setupAdapterClicks() {
        adapter.setOnItemClickListener(object : PlacesAdapter.OnItemClickListener {
            override fun onItemClick(item: PlaceModel) {
                val bundle = Bundle()
                bundle.putInt("id", item.id)
                bundle.putString("destination", "PopularPageFragment")
                findNavController().navigate(R.id.detailPageFragment, bundle)
            }
        })
    }

    private fun fetchData() {
        viewModel.getPopularPlaces()
        viewModel.result.observe(viewLifecycleOwner, Observer {
            result -> result.onSuccess { items ->
                adapter.updateData(items)
            }.onFailure {
                Log.e(ContentValues.TAG, "Error fetching popular places")
            }
        })
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_popularPageFragment_to_mainPageFragment2)
        }
    }
}