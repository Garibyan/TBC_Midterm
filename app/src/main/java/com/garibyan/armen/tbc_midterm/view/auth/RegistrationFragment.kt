package com.garibyan.armen.tbc_midterm.view.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.garibyan.armen.tbc_midterm.databinding.FragmentRegistrationBinding
import com.garibyan.armen.tbc_midterm.view.BaseFragment
import com.garibyan.armen.tbc_midterm.viewmodel.auth.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(
    FragmentRegistrationBinding::inflate
) {

    private val viewModel: RegistrationViewModel by viewModels()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpLogIn.setOnClickListener{
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }

        binding.registerBtn2.setOnClickListener {
            val email = binding.regEmail.text.toString()
            val pass = binding.regPass1.text.toString()
            val confirmPass = binding.regPass2.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
                        } else {
                           Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(activity, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText( activity,"Please fill in all fields", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

