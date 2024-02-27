package com.example.travelapp.presentation.view.auth

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.travelapp.databinding.FragmentLoginBinding
import com.example.travelapp.presentation.view.HomeActivity
import com.example.travelapp.presentation.viewModel.auth.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupValidation()
    }

    private fun setupNavigation() {
        binding.msgSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.forgotPasswordText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        binding.btnNext.setOnClickListener {
            login()
        }
    }

    private fun setupValidation() {
        val textWatchers = arrayOf(
            binding.etEmail,
            binding.etPassword,
        )
        for (watchedText in textWatchers) {
            watchedText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                @SuppressLint("ResourceAsColor")
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val email = binding.etEmail.text.toString()
                    val password = binding.etPassword.text.toString()
                    binding.btnNext.isEnabled = email.isNotEmpty() && password.isNotEmpty()

                    val whiteColor = resources.getColor(R.color.white)
                    binding.btnNext.setTextColor(whiteColor)

                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.login(email, password)
        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }

}