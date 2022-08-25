package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle

import android.view.View

import com.garibyan.armen.tbc_midterm.databinding.FragmentPasswordResetBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
class PasswordResetFragment : BaseFragment<FragmentPasswordResetBinding>(
    FragmentPasswordResetBinding::inflate

) {

    private val resetPassEmail = binding.resetRep
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}