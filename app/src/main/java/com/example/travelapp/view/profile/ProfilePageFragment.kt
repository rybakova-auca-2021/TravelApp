package com.example.travelapp.view.profile

import ProfilePagesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentProfilePageBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfilePageFragment : Fragment() {
    private lateinit var binding: FragmentProfilePageBinding
    private lateinit var adapter: ProfilePagesAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilePageBinding.inflate(inflater, container, false)

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        adapter = ProfilePagesAdapter(requireActivity())
        adapter.addFragment(VisitedPlacesFragment())
        adapter.addFragment(DestinationsFragment())
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Visited"
                1 -> "Destinations"
                else -> "Tab"
            }
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profilePageFragment_to_editProfileFragment)
        }
    }
}