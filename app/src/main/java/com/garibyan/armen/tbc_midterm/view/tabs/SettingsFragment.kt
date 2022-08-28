package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentSettingsBinding
import com.garibyan.armen.tbc_midterm.utils.extentions.findTopNavController
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.SettingsViewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()
        setEmail()
    }

    private fun setEmail(){
        binding.userName.text = AuthenticationManager.auth.currentUser?.email
    }

    private fun onClickListeners() = with(binding){
        logOutBtn.setOnClickListener {
            logOut()
        }
        passChange.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToPasswordChangeFragment())
        }
    }

    private fun logOut(){
        AuthenticationManager.logOut()
        findTopNavController().navigate(R.id.welcomeFragment)
    }

}
