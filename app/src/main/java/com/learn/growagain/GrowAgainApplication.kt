package com.learn.growagain

import android.app.Application
import com.learn.growagain.codelab.testing.data.ServiceLocator
import com.learn.growagain.codelab.testing.data.source.ITaskRepository
import com.learn.growagain.tutorial.di.manually.dependency.LoginContainer

class GrowAgainApplication : Application() {

    val tasITaskRepository: ITaskRepository
        get() = ServiceLocator.provideTaskRepository(this)

    val loginContainer = LoginContainer()
}