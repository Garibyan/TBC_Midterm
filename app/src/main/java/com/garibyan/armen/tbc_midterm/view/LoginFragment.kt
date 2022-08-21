package com.garibyan.armen.tbc_midterm.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.garibyan.armen.tbc_midterm.databinding.FragmentLoginBinding
import com.garibyan.armen.tbc_midterm.viewmodel.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}