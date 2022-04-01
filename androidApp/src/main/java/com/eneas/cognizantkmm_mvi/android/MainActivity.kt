package com.eneas.cognizantkmm_mvi.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eneas.cognizantkmm_mvi.android.R
import com.eneas.cognizantkmm_mvi.android.ui.login.LoginFragment
import com.eneas.cognizantkmm_mvi.android.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .commit()
        }
    }

    fun navigateToProfile() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, ProfileFragment())
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }
}