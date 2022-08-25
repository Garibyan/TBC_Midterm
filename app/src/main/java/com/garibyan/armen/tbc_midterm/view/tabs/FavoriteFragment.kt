package com.garibyan.armen.tbc_midterm.view.tabs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentFavoriteBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.FavoriteViewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}