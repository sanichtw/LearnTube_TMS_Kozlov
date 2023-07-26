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
    private lateinit var binding: FragmentLoginScreenBinding

    private companion object {
        const val REQUIRED_VALUE = 8
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (isInputsValid(email, password)) {
                binding.emailErrorTextView.visibility = View.GONE
                navigateToPostsFragment()
            } else {
                binding.emailErrorTextView.visibility = View.VISIBLE
                binding.emailErrorTextView.text = getString(R.string.incorrect_password_or_email)
            }
        }
    }

    private fun navigateToPostsFragment() {
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

    private fun setupListeners() {
        binding.emailInput.addTextChangedListener(createTextWatcher())
        binding.passwordInput.addTextChangedListener(createTextWatcher())
    }

    private fun updateButtonState() {
        with(binding) {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val emailValid = validateEmail(email)
            val passwordValid = validatePassword(password)

            loginButton.isEnabled = emailValid && passwordValid

            if (!emailValid || !passwordValid) {
                emailErrorTextView.visibility = View.VISIBLE
                emailErrorTextView.text = getString(R.string.incorrect_password_or_email)
            } else {
                emailErrorTextView.visibility = View.GONE
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= REQUIRED_VALUE
    }

    private fun isInputsValid(email: String, password: String): Boolean {
        return validateEmail(email) && validatePassword(password)
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateButtonState()
            }
        }
    }
}