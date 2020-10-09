package com.devmonkey.navigation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.devmonkey.navigation.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // conditional navigation
        loginViewModel.authenticationState.observe(
            viewLifecycleOwner,
            Observer { authenticationState ->
                when (authenticationState) {
                    LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                        // navigate up in the navigation hierarchy
                        navController.navigateUp()
                    }
                    LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> {
                        Snackbar.make(
                            view,
                            R.string.incorrect_username_or_password,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })

        buttonLogin.setOnClickListener {
            loginViewModel.authenticate(
                editTextUserName.text.toString(),
                editTextPassword.text.toString()
            )
        }

        buttonRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController.navigate(action)
        }
    }


}