package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentRegistrationBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.auth.RegistrationViewModel

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(
    FragmentRegistrationBinding::inflate
) {

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            register.setOnClickListener {
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
            }
        }
    }


}