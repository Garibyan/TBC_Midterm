package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentLoginBinding
import com.garibyan.armen.tbc_midterm.repository.BaseRepository
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.view.Inflate
import com.garibyan.armen.tbc_midterm.viewmodel.auth.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        firebaseAuth = FirebaseAuth.getInstance()


        binding.loginSingUp.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }

        binding.logInBtn2.setOnClickListener {
            val email = binding.logInEmail.text.toString()
            val pass = binding.logInPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
                    } else {
                       Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(activity, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}