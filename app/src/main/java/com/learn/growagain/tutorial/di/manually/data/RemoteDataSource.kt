package com.learn.growagain.tutorial.di.manually.data

import com.learn.growagain.tutorial.di.manually.model.User
import retrofit2.Call

class RemoteDataSource(private val loginService: LoginService) {

    fun login(): Call<User> {
        return loginService.login()
    }
}