package com.garibyan.armen.tbc_midterm.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.garibyan.armen.tbc_midterm.databinding.FragmentRegistrationBinding
import com.garibyan.armen.tbc_midterm.viewmodel.RegistrationViewModel

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(
    FragmentRegistrationBinding::inflate
) {

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}