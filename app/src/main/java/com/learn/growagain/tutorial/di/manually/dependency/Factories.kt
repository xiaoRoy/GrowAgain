package com.learn.growagain.tutorial.di.manually.dependency

import com.learn.growagain.tutorial.di.manually.data.UserRepository
import com.learn.growagain.tutorial.di.manually.viewmodel.LoginViewModel


interface Factory<T> {
    fun create(): T
}

class LoginViewModelFactory(private val userRepository: UserRepository):Factory<LoginViewModel> {

    override fun create(): LoginViewModel {
        return LoginViewModel(userRepository)
    }
}