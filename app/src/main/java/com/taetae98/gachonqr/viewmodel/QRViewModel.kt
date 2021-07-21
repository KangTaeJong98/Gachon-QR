package com.taetae98.gachonqr.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.taetae98.gachonqr.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val userRepository: UserRepository
): ViewModel() {
    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("yyyyMMddHHmmss")

    val qr = stateHandle.getLiveData("QR", "")
    val updateTime = stateHandle.getLiveData("UPDATE_TIME", "")

    fun performRefresh() {
        CoroutineScope(Dispatchers.Main).launch {
            val time = System.currentTimeMillis()
            updateTime.value = "Update : ${SimpleDateFormat.getTimeInstance().format(time)}"
            qr.value = "m${userRepository.getStudentId()}${format.format(time)}"
        }
    }
}