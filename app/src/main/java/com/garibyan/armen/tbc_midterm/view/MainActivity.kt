package com.garibyan.armen.tbc_midterm.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.garibyan.armen.tbc_midterm.R
import com.garibyan.armen.tbc_midterm.view.auth.AuthenticationManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        checkSession()
    }

    private fun checkSession(){
        navigation(AuthenticationManager.isLoggedIn())
    }

    private fun navigation(isLoggedIn: Boolean) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        when (isLoggedIn) {
            true -> navController.navigate(R.id.tabsFragment)
            false -> navController.navigate(R.id.welcomeFragment)
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, R.string.double_backpress, Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}