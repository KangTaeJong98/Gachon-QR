package com.taetae98.gachonqr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.BindingFragment
import com.taetae98.gachonqr.databinding.FragmentLoginBinding

class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateSupportActionBar()

        return binding.root
    }

    private fun onCreateSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }
}