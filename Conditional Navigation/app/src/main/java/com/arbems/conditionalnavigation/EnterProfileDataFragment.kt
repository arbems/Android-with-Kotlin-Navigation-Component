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
import kotlinx.android.synthetic.main.fragment_enter_profile_info.*

class EnterProfileDataFragment : Fragment() {
    private val registrationViewModel: RegistrationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_profile_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel.registrationState.observe(viewLifecycleOwner, Observer { registrationState ->
            when(registrationState) {
                RegistrationViewModel.RegistrationState.COLLECT_USER_PASSWORD -> findNavController().navigate(R.id.choose_user_password_fragment)
            }
        })

        val navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            registrationViewModel.userCancelledRegistration()
            navController.popBackStack(R.id.login_fragment, false)
        }

        buttonNext.setOnClickListener {
            registrationViewModel.collectProfileData(editTextFullName?.text?.toString() ?: "", editTextBio?.text?.toString() ?: "")
        }
    }
}