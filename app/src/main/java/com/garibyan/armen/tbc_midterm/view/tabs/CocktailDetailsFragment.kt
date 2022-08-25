package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.garibyan.armen.tbc_midterm.databinding.FragmentCocktailDetailsBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.CocktailDetailsViewModel
class CocktailDetailsFragment : BaseFragment<FragmentCocktailDetailsBinding>(
    FragmentCocktailDetailsBinding::inflate
) {

    private val viewModel: CocktailDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}