package com.taetae98.gachonqr.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.taetae98.gachonqr.LOG_TAG
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.api.LoginAPI
import com.taetae98.gachonqr.databinding.BindingFragment
import com.taetae98.gachonqr.databinding.FragmentLoginBinding
import com.taetae98.gachonqr.dto.request.LoginRequest
import com.taetae98.gachonqr.dto.response.LoginResponse
import com.taetae98.gachonqr.repository.UserRepository
import com.taetae98.gachonqr.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel by viewModels<LoginViewModel>()
    private var loginJob: Job? = null

    @Inject
    lateinit var loginAPI: LoginAPI

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateSupportActionBar()
        onCreateOnLogin()

        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }

    private fun onCreateSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun onCreateOnLogin() {
        binding.setOnLogin {
            viewModel.isInProgress.value = true

            loginJob?.cancel()
            loginJob = CoroutineScope(Dispatchers.IO).launch {
                val response = loginAPI.login(LoginRequest(viewModel.id.value!!, viewModel.password.value!!)).also {
                    Log.d(LOG_TAG, it.toString())
                }

                withContext(Dispatchers.Main) {
                    when (response.code) {
                        LoginResponse.SUCCESS -> {
                            userRepository.setStudentId(response.studentId)
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainActivity())
                            finish()
                        }
                        LoginResponse.NOT_FOUND -> {
                            Snackbar.make(binding.layout, R.string.invalud_account, Snackbar.LENGTH_SHORT).show()
                        }

                        else -> {
                            Snackbar.make(binding.layout, response.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }

                    viewModel.isInProgress.value = false
                }
            }
        }
    }
}