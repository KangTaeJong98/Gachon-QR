package com.taetae98.gachonqr.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.BindingFragment
import com.taetae98.gachonqr.databinding.FragmentSplashBinding
import com.taetae98.gachonqr.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    @Inject
    lateinit var userRepository: UserRepository

    init {
        lifecycleScope.launchWhenStarted {
            if (userRepository.getStudentId() == null) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginActivity())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity())
            }

            finish()
        }
    }
}