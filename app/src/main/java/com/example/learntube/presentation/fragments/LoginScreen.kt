package com.example.learntube.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.learntube.R
import com.example.learntube.databinding.FragmentLoginScreenBinding


class LoginScreen : Fragment() {
    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListenersToInputs()

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val errorText = binding.emailErrorTextView
            val loginButton = binding.loginButton

            if (email.isEmpty() || password.isEmpty()) {
                errorText.apply {
                    text = "Please enter email and password"
                    visibility = View.VISIBLE
                }
            } else {
                errorText.visibility = View.GONE
                loginButton.isEnabled = true

                val navOptions = NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_up)
                    .setExitAnim(R.anim.slide_down)
                    .build()

                findNavController().navigate(
                    R.id.action_LoginScreen_to_PostsScreen,
                    null,
                    navOptions
                )
            }
        }
    }

    private fun setListenersToInputs() {
        binding.emailInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateButtonState()
            }
        })

        binding.passwordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateButtonState()
            }
        })
    }

    private fun updateButtonState() {
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val errorText = binding.emailErrorTextView
        val loginButton = binding.loginButton

        val emailValid = validateEmail(email)
        val passwordValid = validatePassword(password)

        loginButton.isEnabled = emailValid && passwordValid

        if (!emailValid || !passwordValid) {
            errorText.apply {
                text = "Incorrect password or email"
                visibility = View.VISIBLE
            }
        } else {
            errorText.visibility = View.GONE
        }
    }

    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 8
    }
}