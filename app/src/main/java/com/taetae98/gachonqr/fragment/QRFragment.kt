package com.taetae98.gachonqr.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.BindingFragment
import com.taetae98.gachonqr.databinding.FragmentQrBinding
import com.taetae98.gachonqr.repository.UserRepository
import com.taetae98.gachonqr.viewmodel.QRViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QRFragment : BindingFragment<FragmentQrBinding>(R.layout.fragment_qr) {
    private val viewModel by viewModels<QRViewModel>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        setHasOptionsMenu(true)
        lifecycleScope.launchWhenResumed {
            viewModel.performRefresh()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateSupportActionBar()

        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }

    private fun onCreateSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_qr_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                onLogout()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun onLogout() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.logout)
            .setMessage(R.string.are_you_sure_you_want_to_logout)
            .setNegativeButton(R.string.cancel) { _ ,_ ->

            }
            .setPositiveButton(R.string.ok) { _, _ ->
                CoroutineScope(Dispatchers.Main).launch {
                    userRepository.setStudentId(null)
                    findNavController().navigate(QRFragmentDirections.actionQRFragmentToLoginActivity())
                    finish()
                }
            }
            .show()
    }
}