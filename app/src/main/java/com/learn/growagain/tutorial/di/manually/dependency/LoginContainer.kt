package com.learn.growagain.tutorial.di.manually.dependency

import com.learn.growagain.tutorial.di.manually.data.LocalDataSource
import com.learn.growagain.tutorial.di.manually.data.LoginService
import com.learn.growagain.tutorial.di.manually.data.RemoteDataSource
import com.learn.growagain.tutorial.di.manually.data.UserRepository
import retrofit2.Retrofit

class LoginContainer {

    private val retrofit = Retrofit.Builder().baseUrl("https://example.com")
        .build()
        .create(LoginService::class.java)

    private val remoteDataSource = RemoteDataSource(retrofit)

    private val localDataSource = LocalDataSource()

    val userRepository = UserRepository(localDataSource, remoteDataSource)

    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}