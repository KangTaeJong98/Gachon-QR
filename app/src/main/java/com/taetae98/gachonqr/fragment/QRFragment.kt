package com.taetae98.gachonqr.fragment

import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.BindingFragment
import com.taetae98.gachonqr.databinding.FragmentQrBinding
import com.taetae98.gachonqr.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QRFragment : BindingFragment<FragmentQrBinding>(R.layout.fragment_qr) {
    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        CoroutineScope(Dispatchers.Main).launch {
            binding.qr = userRepository.getStudentId()
        }
    }
}