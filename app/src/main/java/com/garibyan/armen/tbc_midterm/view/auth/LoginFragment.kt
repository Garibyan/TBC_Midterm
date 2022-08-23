package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentLoginBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.auth.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            logInBtn2.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
            }
        }
    }
}