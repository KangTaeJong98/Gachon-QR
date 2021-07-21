package com.taetae98.gachonqr.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.taetae98.gachonqr.dto.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    stateHandle: SavedStateHandle
): ViewModel() {
    val id = stateHandle.getLiveData("ID", "")
    val password = stateHandle.getLiveData("PASSWORD", "")

    val isInProgress = stateHandle.getLiveData("IS_IN_PROGRESS", false)

    fun toLoginRequest(): LoginRequest {
        return LoginRequest(
            id.value!!, password.value!!
        )
    }
}