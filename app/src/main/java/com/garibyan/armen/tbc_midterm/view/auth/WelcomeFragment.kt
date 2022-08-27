package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentWelcomeBinding
import com.garibyan.armen.tbc_midterm.extentions.collectLatestFlow
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.auth.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkSession()
        onClickListeners()
    }

    private fun onClickListeners() = with(binding) {
        logInBtn1.setOnClickListener {
            findNavController().navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(
                    null
                )
            )
        }
        registerBtn1.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegistrationFragment())
        }

    }

    private fun checkSession() {
        collectLatestFlow(viewModel.checkUserSession()) {
            if (it != "") {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToTabsFragment())
            }
        }
    }

}