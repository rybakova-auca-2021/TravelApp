package com.example.travelapp.view.auth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentNewPasswordBinding
import com.example.travelapp.viewModel.NewPasswordViewModel

class NewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentNewPasswordBinding
    private val viewModel: NewPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupValidation()
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_newPasswordFragment_to_verificationCodeFragment)
        }
        binding.btnNext.setOnClickListener {
            createNewPassword()
        }
    }

    private fun setupValidation() {
        val textWatchers = arrayOf(
            binding.etPassword,
            binding.etPasswordConfirm
        )
        for (watchedText in textWatchers) {
            watchedText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                @SuppressLint("ResourceAsColor")
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val password = binding.etPassword.text.toString()
                    val passwordConfirm = binding.etPasswordConfirm.text.toString()
                    binding.btnNext.isEnabled = password.isNotEmpty() && passwordConfirm.isNotEmpty()

                    val whiteColor = resources.getColor(R.color.white)
                    binding.btnNext.setTextColor(whiteColor)

                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun createNewPassword() {
        val email = getEmailFromSharedPreferences()
        val password = binding.etPassword.text.toString()
        val repeatedPassword = binding.etPasswordConfirm.text.toString()
        viewModel.createNewPassword(
            password,
            repeatedPassword,
            email,
            onSuccess =  {
                findNavController().navigate(R.id.action_newPasswordFragment_to_loginFragment)
            },
            onError = {
                println("Please, try again")
            }
        )
    }

    private fun getEmailFromSharedPreferences(): String {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", "") ?: ""
    }
}