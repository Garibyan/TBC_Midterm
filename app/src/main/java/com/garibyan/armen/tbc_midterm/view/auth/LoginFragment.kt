package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentLoginBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.auth
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.login
import com.garibyan.armen.tbc_midterm.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logInBtn2.setOnClickListener(this)

        //viewModel.saveEmail("binding.logInEmail.text.toString()")
        onClickListener()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.logInBtn2 -> checkLoginInfo()
        }
    }

    private fun onClickListener() {

        binding.loginSingUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }

        binding.forgotPass.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment())
        }
    }

    private fun checkLoginInputs(): Boolean {
        if (binding.logInPassword.text.isNullOrEmpty())
            return false

        if (binding.logInEmail.text.isNullOrEmpty() || !binding.logInEmail.text.isValidEmail())
            return false

        return true
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun checkLoginInfo() {
        if (checkLoginInputs()) {
            val logInEmail = binding.logInEmail.text.toString()
            val logInPassword = binding.logInPassword.text.toString()
            
            auth.signInWithEmailAndPassword(logInEmail, logInPassword).addOnCompleteListener{
                viewModel.saveEmail(logInEmail)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
            }.addOnCanceledListener{
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
            
        } else
            Toast.makeText(requireContext(), "Input correct login credentials!", Toast.LENGTH_SHORT)
                .show()
    }
}

