package com.devmonkey.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.devmonkey.navigation.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // conditional navigation
        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
                when (authenticationState) {
                    LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                        Toast.makeText(context, R.string.text_welcome, Toast.LENGTH_LONG).show()
                    }
                    LoginViewModel.AuthenticationState.UNAUTHENTICATED, LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> {
                        val action = NavGraphDirections.actionGlobalNavigationLogin()
                        navController.navigate(action)
                    }
                }
            })

        button_logout.setOnClickListener {
            loginViewModel.refuseAuthentication()
        }
    }
}