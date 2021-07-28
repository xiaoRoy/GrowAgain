package com.learn.growagain.tutorial.di.manually

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.learn.growagain.GrowAgainApplication
import com.learn.growagain.tutorial.di.manually.dependency.AppContainer
import com.learn.growagain.tutorial.di.manually.dependency.LoginContainer
import com.learn.growagain.tutorial.di.manually.viewmodel.LoginUserData
import com.learn.growagain.tutorial.di.manually.viewmodel.LoginViewModel

class LoginActivity: FragmentActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var appContainer: AppContainer
    private lateinit var loginUserData: LoginUserData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = (application as GrowAgainApplication).appContainer
        val loginContainer = LoginContainer(appContainer.userRepository).also {
            appContainer.loginContainer = it
        }
        //if we use the login view model more than once place.
        loginViewModel = loginContainer.loginViewModelFactory.create()
        loginUserData = loginContainer.loginUserData
    }

    override fun onDestroy() {
        appContainer.loginContainer = null
        super.onDestroy()
    }
}