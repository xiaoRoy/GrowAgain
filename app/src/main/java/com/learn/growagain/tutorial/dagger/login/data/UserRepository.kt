package com.learn.growagain.tutorial.dagger.login.data

import com.learn.growagain.tutorial.dagger.login.di.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class UserRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) {
}