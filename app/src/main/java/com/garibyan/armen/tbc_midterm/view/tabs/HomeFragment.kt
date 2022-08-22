package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentHomeBinding
import com.garibyan.armen.tbc_midterm.network.Resource
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCocktailById("15346")
        observers()
        binding.login.setOnClickListener {
            // TODO: fix DetailsFragment backstep
            findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToCocktailDetailsFragment())
        }
    }

    private fun observers(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.stateFlow.collectLatest {
                    when (it) {
                        is Resource.Success -> {
                            d("mylog", "Success")
                            d("mylog", it.value.drinks.toString())
                        }
                        is Resource.Error -> {
                            d("mylog", "Error")
                        }
                        is Resource.Loading -> {
                            d("mylog", "Loading")
                        }
                    }
                }
            }
        }
    }

}