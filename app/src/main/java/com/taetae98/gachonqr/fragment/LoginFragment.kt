package com.taetae98.gachonqr.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.TAG
import com.taetae98.gachonqr.api.LoginAPI
import com.taetae98.gachonqr.databinding.BindingFragment
import com.taetae98.gachonqr.databinding.FragmentLoginBinding
import com.taetae98.gachonqr.dto.response.LoginResponse
import com.taetae98.gachonqr.manager.InternetManager
import com.taetae98.gachonqr.repository.UserRepository
import com.taetae98.gachonqr.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.net.SocketTimeoutException
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel by viewModels<LoginViewModel>()
    private var loginJob: Job? = null

    @Inject
    lateinit var loginAPI: LoginAPI

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var internetManager: InternetManager

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
            loginJob?.cancel()
            if (internetManager.isConnected()) {
                loginJob = CoroutineScope(Dispatchers.IO).launch {
                    performLogin()
                }
            } else {
                Snackbar.make(binding.layout, R.string.connect_internet, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun performLogin() {
        try {
            setProgressVisible(true)
            val response = loginAPI.login(viewModel.toLoginRequest())
            when (response.code) {
                LoginResponse.SUCCESS -> {
                    userRepository.setStudentId(response.studentId)
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainActivity())
                    finish()
                }
                LoginResponse.NOT_FOUND -> {
                    showMessage(R.string.invalud_account)
                }
                else -> {
                    showMessage(response.message)
                }
            }
        } catch (e: SocketTimeoutException) {
            Log.e(TAG, "performLogin Timeout", e)
            showMessage(R.string.connect_internet)
        } finally {
            setProgressVisible(false)
        }
    }

    private suspend fun setProgressVisible(boolean: Boolean) {
        withContext(Dispatchers.Main) {
            viewModel.isInProgress.value = boolean
        }
    }

    private suspend fun showMessage(message: String) {
        withContext(Dispatchers.Main) {
            Snackbar.make(binding.layout, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private suspend fun showMessage(@StringRes message: Int) {
        withContext(Dispatchers.Main) {
            Snackbar.make(binding.layout, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}