package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentRecoveryPassword2Binding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager.updatePassword

class PasswordChangeFragment : BaseFragment<FragmentRecoveryPassword2Binding>(
    FragmentRecoveryPassword2Binding::inflate
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

    private fun onClick(){
        binding.recPass2Btn.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword(){
        if(checkInputs()){
            updatePassword(binding.recPass2New.text.toString()){ isSuccess->
                if(isSuccess){
                    Toast.makeText(requireContext(), "Password has changed", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(requireContext(), "Password has not changed", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(requireContext(), "Input is incorrect", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInputs(): Boolean{
        val newPass = binding.recPass2New.text.toString()
        val newPassRep = binding.recPass2Rep.text.toString()

        if(newPass.isNullOrEmpty())
            return false

        if(newPassRep.isNullOrEmpty())
            return false

        if(newPass != newPassRep)
            return false

        return true
    }

}