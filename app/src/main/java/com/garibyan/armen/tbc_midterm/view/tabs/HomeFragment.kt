package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentHomeBinding
import com.garibyan.armen.tbc_midterm.utils.HomeTabRequestTypes
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() = with(binding) {
        txtRandomTenCocktail.setOnClickListener {
            navigateToCocktailListFragment(HomeTabRequestTypes.REQUEST_RANDOM_TEN_COCKTAILS)
        }
        txtLatestCocktails.setOnClickListener {
            navigateToCocktailListFragment(HomeTabRequestTypes.REQUEST_LATEST_COCKTAILS)
        }
        txtPopularCocktails.setOnClickListener {
            navigateToCocktailListFragment(HomeTabRequestTypes.REQUEST_POPULAR_COCKTAILS)
        }
        txtSearchByCategory.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToCategoriesFragment())
        }
    }

    private fun navigateToCocktailListFragment(requestType: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragment2ToCocktailsListFragment(requestType, null)
        )
    }
}