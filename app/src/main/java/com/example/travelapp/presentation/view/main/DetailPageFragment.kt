package com.example.travelapp.presentation.view.main

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentDetailPageBinding
import com.example.travelapp.databinding.FragmentMainPageBinding
import com.example.travelapp.presentation.view.HomeActivity
import com.example.travelapp.presentation.viewModel.main.GetMustVisitListViewModel
import com.example.travelapp.presentation.viewModel.main.GetMustVisitPlaceDetailViewModel
import com.example.travelapp.presentation.viewModel.main.GetPackageDetailViewModel
import com.example.travelapp.presentation.viewModel.main.GetPackagesViewModel
import com.example.travelapp.presentation.viewModel.main.GetPopularDetailViewModel
import com.example.travelapp.presentation.viewModel.main.GetPopularListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailPageFragment : Fragment() {
    private var _binding: FragmentDetailPageBinding? = null
    private val binding: FragmentDetailPageBinding get() = _binding!!
    private val getPopularDetailViewModel: GetPopularDetailViewModel by viewModel()
    private val getMustVisitDetailViewModel: GetMustVisitPlaceDetailViewModel by viewModel()
    private val getPackagesDetailViewModel: GetPackageDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        (requireActivity() as HomeActivity).hideBtmNav()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        val destination = arguments?.getString("destination")
        val detail = arguments?.getString("detail")
        if (destination != null) {
            setupNavigation(destination)
        }
        if (id != null) {
            when (detail) {
                "popular" -> {
                    fetchPopularDetail(id)
                }
                "mustvisit" -> {
                    fetchMustVisitDetail(id)
                }
                else -> {
                    fetchPackageDetail(id)
                }
            }
        }
    }

    private fun setupNavigation(destination: String) {
        binding.btnBack.setOnClickListener {
            when(destination) {
                "MainPageFragment" -> {
                    findNavController().navigate(R.id.mainPageFragment2)
                }
                "PopularPageFragment" -> {
                    findNavController().navigate(R.id.popularPageFragment)
                }
                "MustVisitPageFragment" -> {
                    findNavController().navigate(R.id.mustVisitPageFragment)
                }
                "PackagesPageFragment" -> {
                    findNavController().navigate(R.id.packagesPageFragment)
                }
            }
        }
    }

    private fun fetchPopularDetail(id: Int) {
        getPopularDetailViewModel.getPopularPlaces(id)
        getPopularDetailViewModel.result.observe(viewLifecycleOwner, Observer {
                result -> result.onSuccess { item ->
            binding.detailPlaceName.text = item.name
            binding.detailDescription.text = item.description
            Glide.with(binding.detailImg.context)
                .load(item.main_image)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.detailImg)
            }.onFailure { error ->
                Log.e(ContentValues.TAG, "Error fetching popular places: $error")
            }
        })
    }

    private fun fetchMustVisitDetail(id: Int) {
        getMustVisitDetailViewModel.getMustVisitDetailPlaces(id)
        getMustVisitDetailViewModel.result.observe(viewLifecycleOwner, Observer {
                result -> result.onSuccess { item ->
            binding.detailPlaceName.text = item.name
            binding.detailDescription.text = item.description
            Glide.with(binding.detailImg.context)
                .load(item.main_image)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.detailImg)
            }.onFailure { error ->
                Log.e(ContentValues.TAG, "Error fetching must visit places: $error")
            }
        })
    }

    private fun fetchPackageDetail(id: Int) {
        getPackagesDetailViewModel.getMustVisitDetailPlaces(id)
        getPackagesDetailViewModel.result.observe(viewLifecycleOwner, Observer {
                result -> result.onSuccess { item ->
            binding.detailPlaceName.text = item.name
            binding.detailDescription.text = item.description
            Glide.with(binding.detailImg.context)
                .load(item.main_image)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.detailImg)
            }.onFailure { error ->
                Log.e(ContentValues.TAG, "Error fetching packages: $error")
            }
        })
    }
}