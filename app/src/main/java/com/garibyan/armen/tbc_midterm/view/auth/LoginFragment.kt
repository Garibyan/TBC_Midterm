package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentLoginBinding
import com.garibyan.armen.tbc_midterm.repository.BaseRepository
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.Inflate
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.getUser
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.isLoggedIn
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.login
import com.garibyan.armen.tbc_midterm.viewmodel.auth.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

     private fun onClick(){
        binding.logInBtn2.setOnClickListener {
            checkLoginInfo()
        }

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
            login(
                binding.logInEmail.text.toString(),
                binding.logInPassword.text.toString()
            ) { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Couldn't login, try again later!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else
            Toast.makeText(
                requireContext(),
                "Input correct login credentionals!",
                Toast.LENGTH_SHORT
            ).show()
    }

    override fun onStart() {
        super.onStart()
        if (isLoggedIn()) {
            getUser {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
            }
        }
    }
}