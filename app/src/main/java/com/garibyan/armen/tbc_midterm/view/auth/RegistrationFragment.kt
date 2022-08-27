package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentRegistrationBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.register

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(
    FragmentRegistrationBinding::inflate
), View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        binding.registerBtn2.setOnClickListener(this)

        binding.signUpLogIn.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment(null))
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
        if (checkInputs()) {
            val registerEmail = binding.regEmail.text.toString()
            val registerPassword = binding.regPass1.text.toString()
            val user = User(registerEmail)
            register(user, registerPassword) { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment(registerEmail))
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Couldn't create account, try again later!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else
            Toast.makeText(requireContext(), "Your input is not correct", Toast.LENGTH_SHORT).show()
    }

}


