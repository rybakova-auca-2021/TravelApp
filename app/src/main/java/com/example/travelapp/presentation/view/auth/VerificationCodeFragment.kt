package com.example.travelapp.presentation.view.auth

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
import com.example.travelapp.databinding.FragmentVerificationCodeBinding
import com.example.travelapp.presentation.viewModel.auth.CodeVerificationViewModel

class VerificationCodeFragment : Fragment() {
    private lateinit var binding: FragmentVerificationCodeBinding
    private val viewModel: CodeVerificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerificationCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupValidation()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_verificationCodeFragment_to_forgotPasswordFragment)
        }
        binding.btnNext.setOnClickListener {
            verifyCode()
        }
    }

    private fun setupValidation() {
        val textWatchers = arrayOf(
            binding.pin,
        )
        for (watchedText in textWatchers) {
            watchedText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                @SuppressLint("ResourceAsColor")
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val pin = binding.pin.text.toString()
                    binding.btnNext.isEnabled = pin.isNotEmpty()

                    val whiteColor = resources.getColor(R.color.white)
                    binding.btnNext.setTextColor(whiteColor)

                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun verifyCode() {
        val email = getEmailFromSharedPreferences()
        val code = binding.pin.text.toString()
        viewModel.verifyCode(code, email,
            onSuccess = {
                findNavController().navigate(R.id.action_verificationCodeFragment_to_newPasswordFragment)
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