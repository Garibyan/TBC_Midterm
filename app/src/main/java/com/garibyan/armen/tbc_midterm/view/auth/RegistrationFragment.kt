package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.auth.User
import com.garibyan.armen.tbc_midterm.databinding.FragmentRegistrationBinding
import com.garibyan.armen.tbc_midterm.utils.extentions.toast
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
                    requireContext().toast(getString(R.string.couldnot_create_account_try_again_later))
                }
            }
        } else
        requireContext().toast(getString(R.string.your_input_is_not_correct))

    }

}


