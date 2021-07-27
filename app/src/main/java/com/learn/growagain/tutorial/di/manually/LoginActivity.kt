package com.learn.growagain.tutorial.di.manually

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.learn.growagain.GrowAgainApplication
import com.learn.growagain.tutorial.di.manually.viewmodel.LoginViewModel

class LoginActivity: FragmentActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginContainer = (application as GrowAgainApplication).loginContainer
//        loginViewModel = LoginViewModel(loginContainer.userRepository)

        //if we use the login view model more than once place.
        loginViewModel = loginContainer.loginViewModelFactory.create()
    }
}