package com.learn.growagain.tutorial.dagger.login.di

import com.learn.growagain.tutorial.dagger.login.data.UserRepository
import dagger.Component

@ApplicationScope
@Component
interface ApplicationComponent {

    fun userRepository(): UserRepository
}