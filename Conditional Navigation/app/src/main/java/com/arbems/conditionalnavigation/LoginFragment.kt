package com.arbems.conditionalnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.navigate(R.id.profile_fragment)
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> Snackbar.make(
                    view,
                    R.string.incorrect_username_or_password,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        buttonLogin.setOnClickListener {
            viewModel.authenticate(
                editTextFullName.text.toString(),
                editTextPassword.text.toString()
            )
        }

        buttonRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToNavigation()
            findNavController().navigate(action)
        }
    }
}