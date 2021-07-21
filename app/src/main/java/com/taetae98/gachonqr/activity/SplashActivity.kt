package com.taetae98.gachonqr.activity

import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.ActivitySplashBinding
import com.taetae98.gachonqr.databinding.BindingActivity

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.splashFragment)
        )
    }
}