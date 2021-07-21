package com.taetae98.gachonqr.activity

import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.gachonqr.R
import com.taetae98.gachonqr.databinding.ActivityLoginBinding
import com.taetae98.gachonqr.databinding.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.loginFragment)
        )
    }
}