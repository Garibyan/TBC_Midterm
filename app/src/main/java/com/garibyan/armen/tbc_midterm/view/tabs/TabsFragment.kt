package com.garibyan.armen.tbc_midterm.view.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.databinding.FragmentTabsBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.tabs.TabsViewModel


class TabsFragment : BaseFragment<FragmentTabsBinding>(
    FragmentTabsBinding::inflate
) {

    private val viewModel: TabsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

}