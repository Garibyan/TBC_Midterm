package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentRegistrationBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.register
import com.garibyan.armen.tbc_midterm.viewmodel.auth.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(
    FragmentRegistrationBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        registerAccount()

        binding.signUpLogIn.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())

        }
    }

        private fun checkInputs(): Boolean {

            if (binding.regPass1.text.isNullOrEmpty())
                return false

            if (binding.regPass2.text.isNullOrEmpty())
                return false


            if (binding.regEmail.text.isNullOrEmpty() || !binding.regEmail.text.isValidEmail())
                return false

            return true
        }

        private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


    private fun registerAccount() {
        if (checkInputs()){
            val user = User( binding.regEmail.text.toString())
            register(user, binding.regPass1.text.toString()) { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Couldn't create account, try again later!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        else
            Toast.makeText(requireContext(), "Your input is not correct", Toast.LENGTH_SHORT).show()
    }



    }


