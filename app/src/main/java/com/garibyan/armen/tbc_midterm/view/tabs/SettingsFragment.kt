package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentSettingsBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager
import com.garibyan.armen.tbc_midterm.view.auth.User


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {
    private  var mailTextView = binding.userName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mailTextView.text = User.UserInstance.userData!!.email

        onClick()
    }
    private fun onClick (){
            binding.logOutBtn.setOnClickListener {
                AuthenticationManager.logOut()
                findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToWelcomeFragment2())
            }
        }
    }

