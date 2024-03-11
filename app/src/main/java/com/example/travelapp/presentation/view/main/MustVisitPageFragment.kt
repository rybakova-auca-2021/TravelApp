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
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.data.model.PlaceModel
import com.example.travelapp.databinding.FragmentMustVisitPageBinding
import com.example.travelapp.presentation.view.HomeActivity
import com.example.travelapp.presentation.viewModel.main.GetMustVisitListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MustVisitPageFragment : Fragment() {
    private var _binding: FragmentMustVisitPageBinding? = null
    private val binding: FragmentMustVisitPageBinding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlacesAdapter
    private val viewModel: GetMustVisitListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMustVisitPageBinding.inflate(inflater, container, false)
        recyclerView = binding.rvMustVisit
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
                bundle.putString("destination", "MustVisitPageFragment")
                findNavController().navigate(R.id.detailPageFragment, bundle)
            }
        })
    }

    private fun fetchData() {
        viewModel.getMustVisitPlaces()
        viewModel.result.observe(viewLifecycleOwner, Observer {
                result -> result.onSuccess { items ->
                adapter.updateData(items)
            }.onFailure {
                Log.e(ContentValues.TAG, "Error fetching must visit places")
            }
        })
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_mustVisitPageFragment_to_mainPageFragment2)
        }
    }
}