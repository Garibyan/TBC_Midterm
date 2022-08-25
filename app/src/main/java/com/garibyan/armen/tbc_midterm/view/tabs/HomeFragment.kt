package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_midterm.adapters.CocktailsAdapter
import com.garibyan.armen.tbc_midterm.databinding.FragmentHomeBinding
import com.garibyan.armen.tbc_midterm.extentions.collectLatestFlow
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktails
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()
    private val cocktailsAdapter: CocktailsAdapter by lazy { CocktailsAdapter() }
    private var latestBtnClicked: Int = 0

    companion object{
        const val POPULAR_COCKTAILS = 1
        const val LATEST_COCKTAILS = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() = with(binding) {
        btnPopularCocktails.setOnClickListener {
            viewModel.getPopularCocktails()
            observers(POPULAR_COCKTAILS)
            latestBtnClicked = POPULAR_COCKTAILS
            d("latestBtnClicked", latestBtnClicked.toString())
        }
        btnLatestCocktails.setOnClickListener {
            viewModel.getLatestCocktails()
            observers(LATEST_COCKTAILS)
            latestBtnClicked = LATEST_COCKTAILS

            d("latestBtnClicked", latestBtnClicked.toString())
        }
        btnRetry.setOnClickListener {
            viewModel.getLatestCocktails()
            observers(latestBtnClicked)

            d("latestBtnClicked", latestBtnClicked.toString())
        }
        cocktailsAdapter.onItemClickListener = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragment2ToCocktailDetailsFragment(it)
            )
        }
    }

    private fun initRecyclerView(cocktailsList: List<Cocktail>) {
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cocktailsAdapter
            cocktailsAdapter.submitList(cocktailsList)
        }
    }

    private fun observers(type: Int) {
        when (type) {
            POPULAR_COCKTAILS -> collectLatestFlow(viewModel.popularCocktails) { stateAbc(it) }
            LATEST_COCKTAILS -> collectLatestFlow(viewModel.latestCocktails) { stateAbc(it) }
        }
    }

    private fun stateAbc(resource: Resource<Cocktails>) {
        when (resource) {
            is Resource.Success -> {
                d("mylog", "Success")
                successfulState()
                initRecyclerView(resource.value.drinks)
            }
            is Resource.Error -> {
                errorState(resource.isNetworkError!!)
                d("mylog", "Error")
            }
            is Resource.Loading -> {
                loadingState()
                d("mylog", "Loading")
            }
        }
    }

    private fun successfulState() = with(binding){
        progressBar.visibility = View.GONE
        btnRetry.visibility = View.GONE
        rvMain.visibility = View.VISIBLE
    }

    private fun errorState(isNetworkError: Boolean)= with(binding) {
        progressBar.visibility = View.GONE
        btnRetry.visibility = View.VISIBLE
        rvMain.visibility = View.GONE
        if (isNetworkError) {
            Toast.makeText(
                requireContext(),
                "Network error",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireContext(),
                "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun loadingState() = with(binding) {
        binding.progressBar.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
        rvMain.visibility = View.GONE
    }



}