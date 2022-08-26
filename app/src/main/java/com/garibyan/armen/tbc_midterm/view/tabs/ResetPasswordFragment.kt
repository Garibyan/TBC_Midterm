package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentRecoveryPasswordBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.passwordReset

class ResetPasswordFragment : BaseFragment<FragmentRecoveryPasswordBinding>(
    FragmentRecoveryPasswordBinding::inflate
){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

    private fun onClick(){
        binding.recPassBtn.setOnClickListener {
            resetPassword()
        }

        binding.recPassBtn.setOnClickListener{
            findNavController().navigate(ResetPasswordFragmentDirections.actionResetPasswordFragmentToPasswordChangeFragment())
        }
    }

    private fun resetPassword(){
        if(binding.resetPassEmail.text.isNotEmpty() && binding.resetPassEmail.text.isValidEmail()){
            passwordReset(binding.resetPassEmail.text.toString()){ isSuccess ->
                if(isSuccess)
                    Toast.makeText(requireContext(), "Reset message is sent on the mail!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(requireContext(), "Try again later", Toast.LENGTH_SHORT).show()

                findNavController().popBackStack()
            }
        }
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}