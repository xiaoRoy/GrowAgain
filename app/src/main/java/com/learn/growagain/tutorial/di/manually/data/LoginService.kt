package com.learn.growagain.tutorial.di.manually.data

import com.learn.growagain.tutorial.di.manually.model.User
import retrofit2.Call


interface LoginService {

    fun login(): Call<User>
}