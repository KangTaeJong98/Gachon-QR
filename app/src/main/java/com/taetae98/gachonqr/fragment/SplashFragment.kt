package com.taetae98.gachonqr.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.BindingFragment
import com.taetae98.gachonqr.databinding.FragmentSplashBinding

class SplashFragment : BindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {
    init {
        lifecycleScope.launchWhenCreated {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginActivity())
        }
    }
}