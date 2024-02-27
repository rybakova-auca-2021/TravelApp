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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentForgotPasswordBinding
import com.example.travelapp.presentation.viewModel.auth.ResetPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: ResetPasswordViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupValidation()
    }

    private fun setupNavigation() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }
        binding.btnNext.setOnClickListener {
            resetPassword()
        }
    }

    private fun setupValidation() {
        val textWatchers = arrayOf(
            binding.etEmail,
        )
        for (watchedText in textWatchers) {
            watchedText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                @SuppressLint("ResourceAsColor")
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val email = binding.etEmail.text.toString()
                    binding.btnNext.isEnabled = email.isNotEmpty()

                    val whiteColor = resources.getColor(R.color.white)
                    binding.btnNext.setTextColor(whiteColor)

                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun resetPassword() {
        val email = binding.etEmail.text.toString()
        viewModel.resetPassword(email)
        viewModel.result.observe(viewLifecycleOwner, Observer {
            saveEmailToSharedPreferences(email)
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_verificationCodeFragment)
        })
    }

    private fun saveEmailToSharedPreferences(email: String) {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }
}