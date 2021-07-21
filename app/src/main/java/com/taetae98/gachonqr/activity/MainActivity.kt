package com.taetae98.gachonqr.activity

import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.ActivityMainBinding
import com.taetae98.gachonqr.databinding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.QRFragment)
        )
    }
}