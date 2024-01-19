package com.example.travelapp.view.auth

import android.annotation.SuppressLint
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
import com.example.travelapp.databinding.FragmentSignUpBinding
import com.example.travelapp.viewModel.RegisterViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupValidation()
    }

    private fun setupNavigation() {
        binding.msgLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
            register()
        }
    }

    private fun setupValidation() {
        val textWatchers = arrayOf(
            binding.etEmail,
            binding.etPassword,
            binding.etPasswordConfirm
        )
        for (watchedText in textWatchers) {
            watchedText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                @SuppressLint("ResourceAsColor")
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val email = binding.etEmail.text.toString()
                    val password = binding.etPassword.text.toString()
                    val repeatedPassword = binding.etPasswordConfirm.text.toString()
                    binding.btnSignUp.isEnabled = email.isNotEmpty() && password.isNotEmpty() && repeatedPassword.isNotEmpty()

                    val whiteColor = resources.getColor(R.color.white)
                    binding.btnSignUp.setTextColor(whiteColor)

                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun register() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatedPassword = binding.etPasswordConfirm.text.toString()

        viewModel.register(email, password, repeatedPassword,
            onSuccess = {
                findNavController().navigate(R.id.action_signUpFragment_to_mainPageFragment)
            },
            onError = {
                println("try again")
            }
        )
    }
}