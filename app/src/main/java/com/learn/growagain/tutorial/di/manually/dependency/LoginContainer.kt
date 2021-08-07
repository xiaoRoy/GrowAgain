package com.learn.growagain.tutorial.di.manually.dependency

import com.learn.growagain.tutorial.di.manually.data.UserRepository
import com.learn.growagain.tutorial.di.manually.viewmodel.LoginUserData

class LoginContainer(private val userRepository: UserRepository) {

    val loginUserData = LoginUserData()

    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}