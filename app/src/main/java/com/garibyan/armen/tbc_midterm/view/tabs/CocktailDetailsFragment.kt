package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import coil.load
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentCocktailDetailsBinding
import com.garibyan.armen.tbc_midterm.extentions.collectLatestFlow
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.CocktailDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailsFragment : BaseFragment<FragmentCocktailDetailsBinding>(
    FragmentCocktailDetailsBinding::inflate
) {

    private val viewModel: CocktailDetailsViewModel by viewModels()
    private val args by navArgs<CocktailDetailsFragmentArgs>()
    private var isFavorite: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCocktailById(args.cocktailId)
        observers()
        onClickListeners()
        d("state", args.cocktailId)
    }

    private fun observers() {
        collectLatestFlow(viewModel.cocktailFlow) {
            when (it) {
                is Resource.Success -> {
                    Log.d("state", "Success")
                    Log.d("state", it.value.toString())
                    successfulState(it.value)
                }
                is Resource.Error -> {
                    errorState(it.isNetworkError!!)
                    Log.d("state", "Error")
                }
                is Resource.Loading -> {
                    loadingState()
                    Log.d("state", "Loading")
                }
            }
        }
    }

    private fun initView(cocktail: Cocktail) = with(binding) {
        imgCocktailImg.load(cocktail.strDrinkThumb)
        txtTitle.text = cocktail.name
        txtCategoryName.text = cocktail.strCategory
        txtGlassName.text = cocktail.strGlass
        txtAlcoholName.text = cocktail.strAlcoholic
        txtInstruction.text = cocktail.strInstructions
    }

    private fun successfulState(cocktail: Cocktail) = with(binding) {
        progressBar.visibility = View.GONE
        mainInfo.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
        initView(cocktail)
    }

    private fun errorState(isNetworkError: Boolean) = with(binding) {
        progressBar.visibility = View.GONE
        mainInfo.visibility = View.GONE
        btnRetry.visibility = View.VISIBLE
        if (isNetworkError) {
            Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadingState() = with(binding) {
        binding.progressBar.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
        mainInfo.visibility = View.GONE
    }


    private fun onClickListeners() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        btnFavorite.setOnClickListener {
            isFavorite = !isFavorite
            isFavorite()
        }
        btnRetry.setOnClickListener {
            viewModel.getCocktailById(args.cocktailId)
        }
    }

    private fun isFavorite() {
        if (isFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_not_favorite)
        }
    }

}