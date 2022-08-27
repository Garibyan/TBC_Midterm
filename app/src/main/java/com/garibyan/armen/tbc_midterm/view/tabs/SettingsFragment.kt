package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentSettingsBinding
import com.garibyan.armen.tbc_midterm.extentions.findTopNavController
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEmail()
        onClickListeners()
    }

    private fun setEmail(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getSavedEmail().collect{
                    binding.userName.text = it
                    d("email", it)
                }
            }
        }
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
        viewModel.clear()
        findTopNavController().navigate(R.id.welcomeFragment)
    }

}
