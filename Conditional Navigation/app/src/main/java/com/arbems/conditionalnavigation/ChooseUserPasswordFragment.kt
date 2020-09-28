package com.arbems.conditionalnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_choose_user_password.*

class ChooseUserPasswordFragment : Fragment() {
    private val registrationViewModel: RegistrationViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_user_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        registrationViewModel.registrationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                RegistrationViewModel.RegistrationState.REGISTRATION_COMPLETED -> {
                    val authToken = registrationViewModel.authToken
                    loginViewModel.authenticate(authToken)
                    navController.popBackStack(R.id.profile_fragment, false)
                }
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            registrationViewModel.userCancelledRegistration()
            navController.popBackStack(R.id.login_fragment, false)
        }

        buttonRegister.setOnClickListener {
            registrationViewModel.createAccountAndLogin(
                editTextUserName?.text?.toString() ?: "",
                editTextPassword?.text?.toString() ?: ""
            )
        }
    }
}