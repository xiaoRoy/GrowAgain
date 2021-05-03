package com.learn.growagain.tutorial.coroutine.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserFragment : Fragment() {

    private val userScope = UserScope()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showOneUser()
        MainScope()
        shoTwoUsersGettingParallel()
        showTwoUserGettingSeries()
    }

    private fun showOneUser() {
        userScope.launch(Dispatchers.Main) {
            val user = fetchUser()
            showUser(user)
        }
    }

    private suspend fun fetchUser(): User {
        return userScope.async(Dispatchers.IO) {
            delay(1000L)
            User()
        }.await()
    }

    private fun showUser(user: User) {
        // show user on UI
    }

    private suspend fun fetchFirstUser(): User {
        delay(1000)
        return User()
    }

    private suspend fun fetchSecondUser(): User {
        delay(1000)
        return User()
    }

    private fun shoTwoUsersGettingParallel() {
        userScope.launch {
            val scope = this
            val firstUser = scope.async(Dispatchers.IO) { fetchFirstUser() }
            val secondUser = scope.async(Dispatchers.IO) { fetchSecondUser() }
            showUsers(firstUser.await(), secondUser.await())
        }
    }

    private fun showTwoUserGettingSeries() {
        userScope.launch {
            val scope = this
            val firstUser = withContext(Dispatchers.IO) { fetchFirstUser() }
            val secondUser = withContext(Dispatchers.IO) { fetchSecondUser() }
            showUsers(firstUser, secondUser)
        }
    }

    override fun onDestroy() {
        userScope.cancel()
        super.onDestroy()
    }

    private fun showUsers(first: User, second: User) {
        // show users on UI
    }
}

private class User {

}

private class UserScope: CoroutineScope {

    private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob
}