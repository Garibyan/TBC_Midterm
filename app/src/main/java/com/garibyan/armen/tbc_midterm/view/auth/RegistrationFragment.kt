package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentRegistrationBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.register
import com.garibyan.armen.tbc_midterm.viewmodel.auth.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(
    FragmentRegistrationBinding::inflate
),View.OnClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener(){
        binding.registerBtn2.setOnClickListener(this)

        binding.signUpLogIn.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.registerBtn2 -> registerAccount()
        }
    }

        private fun checkInputs(): Boolean {

            if (binding.regPass1.text.isNullOrEmpty())
                return false

            if (binding.regEmail.text.isNullOrEmpty() || !binding.regEmail.text.isValidEmail())
                return false

            return true
        }

        private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


    private fun registerAccount() {
        if (checkInputs()){
            var registerEmail = binding.regEmail
            var registerPassword = binding.regPass1
            val user = User(registerEmail.text.toString())
            register(user, registerPassword.text.toString()) { isSuccess ->
                if (isSuccess) {
                    navigateLogInPage()
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

    private fun navigateLogInPage() {
        findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
    }


}


