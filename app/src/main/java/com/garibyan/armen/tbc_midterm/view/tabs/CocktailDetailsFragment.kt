package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.garibyan.armen.tbc_midterm.databinding.FragmentCocktailDetailsBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.CocktailDetailsViewModel

class CocktailDetailsFragment : BaseFragment<FragmentCocktailDetailsBinding>(
    FragmentCocktailDetailsBinding::inflate
) {

    private val viewModel: CocktailDetailsViewModel by viewModels()
    private val args by navArgs<CocktailDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClickListeners()
    }

    private fun initView() = with(binding) {
        imgCocktailImg.load(args.cocktail.strDrinkThumb)
        txtTitle.text = args.cocktail.name
        txtCategoryName.text = args.cocktail.strCategory
        txtGlassName.text = args.cocktail.strGlass
        txtAlcoholName.text = args.cocktail.strAlcoholic
        txtInstruction.text = args.cocktail.strInstructions
    }

    private fun onClickListeners() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}