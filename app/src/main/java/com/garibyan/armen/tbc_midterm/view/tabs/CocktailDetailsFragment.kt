package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.adapters.IngredientAdapter
import com.garibyan.armen.tbc_midterm.databinding.FragmentCocktailDetailsBinding
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail
import com.garibyan.armen.tbc_midterm.network.responcemodels.Ingredient
import com.garibyan.armen.tbc_midterm.utils.DetailsTabRequestType
import com.garibyan.armen.tbc_midterm.utils.HomeTabRequestTypes
import com.garibyan.armen.tbc_midterm.utils.extentions.collectLatestFlow
import com.garibyan.armen.tbc_midterm.utils.extentions.toast
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.CocktailDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailsFragment : BaseFragment<FragmentCocktailDetailsBinding>(
    FragmentCocktailDetailsBinding::inflate
) {

    private val viewModel: CocktailDetailsViewModel by viewModels()
    private val args by navArgs<CocktailDetailsFragmentArgs>()
    private val ingredientsAdapter: IngredientAdapter by lazy { IngredientAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRequestByType()
        observers()
        onClickListeners()
    }

    private fun onClickListeners() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        btnRetry.setOnClickListener {
            getRequestByType()
        }
        ingredientsAdapter.onItemClickListener = {
            findNavController().navigate(
                CocktailDetailsFragmentDirections.actionCocktailDetailsFragmentToCocktailsListFragment(
                    HomeTabRequestTypes.REQUEST_SEARCH_BY_INGREDIENT, it)
            )
        }
    }

    private fun getRequestByType() {
        when (args.requestType) {
            DetailsTabRequestType.REQUEST_COCKTAIL_BY_ID -> {
                viewModel.getCocktailById(args.cocktailId!!)
            }
            DetailsTabRequestType.REQUEST_RANDOM_COCKTAIL -> {
                viewModel.getRandomCocktail()
            }
        }
    }

    private fun observers() {
        collectLatestFlow(viewModel.cocktailFlow) {
            when (it) {
                is Resource.Success -> {
                    Log.d("state", "Success")
                    Log.d("state", it.value.toString())
                    successfulState(it.value.drinks[0])
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
        txtInstruction.movementMethod = ScrollingMovementMethod()
        imgCocktailImg.load(cocktail.strDrinkThumb){
            transformations(RoundedCornersTransformation(20f))
        }
        txtTitle.text = cocktail.name
        txtCategoryName.text = cocktail.strCategory
        txtGlassName.text = cocktail.strGlass
        txtAlcoholName.text = cocktail.strAlcoholic
        txtInstruction.text = cocktail.strInstructions
        rvIngredients.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = ingredientsAdapter
            ingredientsAdapter.submitList(getIngredientsList(cocktail))
        }
    }

    private fun getIngredientsList(cocktail: Cocktail): MutableList<Ingredient> {
        val listOfIngredients = mutableListOf<Ingredient>()
        cocktail.strIngredient1?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient2?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient3?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient4?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient5?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient6?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient7?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient8?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient9?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient10?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient11?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient12?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient13?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient14?.let { listOfIngredients.add(Ingredient(it)) }
        cocktail.strIngredient15?.let { listOfIngredients.add(Ingredient(it)) }
        return listOfIngredients
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
            requireContext().toast(getString(R.string.network_error))
        } else {
            requireContext().toast(getString(R.string.something_went_wrong))
        }
    }

    private fun loadingState() = with(binding) {
        binding.progressBar.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
        mainInfo.visibility = View.GONE
    }
}