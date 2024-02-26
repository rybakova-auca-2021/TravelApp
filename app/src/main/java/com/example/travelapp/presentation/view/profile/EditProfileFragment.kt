package com.example.travelapp.presentation.view.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentEditProfileBinding
import com.example.travelapp.presentation.view.HomeActivity
import com.example.travelapp.presentation.viewModel.profile.EditProfileViewModel
import com.example.travelapp.presentation.viewModel.profile.GetProfileViewModel

class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: EditProfileViewModel by viewModels()
    private val userDataViewModel: GetProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        (requireActivity() as HomeActivity).hideBtmNav()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProfileData()
        setupValidation()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_editProfileFragment_to_profilePageFragment)
        }
        binding.btnSave.setOnClickListener {
            editProfile()
        }
    }

    private fun setupValidation() {
        binding.etFirstName.addTextChangedListener(textWatcher)
        binding.etLastName.addTextChangedListener(textWatcher)
        binding.etEmail.addTextChangedListener(textWatcher)
        binding.etLocation.addTextChangedListener(textWatcher)
        binding.etPhoneNumber.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val name = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val location = binding.etLocation.text.toString().trim()
            val phone = binding.etPhoneNumber.text.toString().trim()

            val isNotEmpty = name.isNotEmpty() && lastName.isNotEmpty() &&
                    email.isNotEmpty() && location.isNotEmpty() && phone.isNotEmpty()

            binding.btnSave.isEnabled = isNotEmpty
        }
    }

    private fun getProfileData() {
        userDataViewModel.getUserProfile(
            onSuccess = { userProfile ->
                binding.etFirstName.text = userProfile.first_name.toEditable()
                binding.etLastName.text = userProfile.last_name.toEditable()
                binding.etLocation.text = userProfile.profile.location?.toEditable()
                binding.etPhoneNumber.text = userProfile.profile.phone_number?.toEditable()
                binding.etEmail.text = userProfile.email.toEditable()
            },
            onError = {
                // Handle error if needed
            }
        )
    }

    private fun String?.toEditable(): Editable? {
        return this?.let { Editable.Factory.getInstance().newEditable(it) }
    }


    private fun editProfile() {
        val name = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val location = binding.etLocation.text.toString()
        val phone = binding.etPhoneNumber.text.toString()
        viewModel.editUserProfile(name, lastName, email, location, phone,
            onSuccess = {
                findNavController().navigate(R.id.profilePageFragment)
            },
            onError = {
                print("try again")
            }
        )
    }
}