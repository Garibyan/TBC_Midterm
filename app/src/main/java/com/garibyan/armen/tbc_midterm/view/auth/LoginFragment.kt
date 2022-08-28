package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentLoginBinding
import com.garibyan.armen.tbc_midterm.utils.extentions.toast
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.login
import com.garibyan.armen.tbc_midterm.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModels()
    private val args by navArgs<LoginFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logInBtn2.setOnClickListener(this)

        setEmail()
        onClickListener()
    }

    private fun setEmail() {
        if (args.email != null) {
            binding.logInEmail.setText(args.email)
        }
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
            val logInEmail = binding.logInEmail
            val logInPassword = binding.logInPassword

            login(logInEmail.text.toString(), logInPassword.text.toString()) { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
                } else {
                    requireContext().toast(
                        getString(R.string.couldnot_login_try_again_later),
                    )
                }
            }
        } else
                requireContext().toast(getString(R.string.input_correct_login_credentials))
    }
}

