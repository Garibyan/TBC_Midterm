package com.garibyan.armen.tbc_midterm.view.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentWelcomeBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.auth.WelcomeViewModel

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            logInBtn1.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment())
            }
            registerBtn1.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegistrationFragment())
            }
        }

    }

}