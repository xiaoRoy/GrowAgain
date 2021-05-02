package com.learn.growagain.tutorial.coroutine.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*

class UserFragment : Fragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showOneUser()
        MainScope()
        shoTwoUsersGettingParallel()
        showTwoUserGettingSeries()
    }

    private fun showOneUser() {
        GlobalScope.launch(Dispatchers.Main) {
            val user = fetchUser()
            showUser(user)
        }
    }

    private suspend fun fetchUser(): User {
        return GlobalScope.async(Dispatchers.IO) {
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
        GlobalScope.launch {
            val scope = this
            val firstUser = scope.async(Dispatchers.IO) { fetchFirstUser() }
            val secondUser = scope.async(Dispatchers.IO) { fetchSecondUser() }
            showUsers(firstUser.await(), secondUser.await())
        }
    }

    private fun showTwoUserGettingSeries() {
        GlobalScope.launch {
            val scope = this
            val firstUser = withContext(Dispatchers.IO) { fetchFirstUser() }
            val secondUser = withContext(Dispatchers.IO) { fetchSecondUser() }
            showUsers(firstUser, secondUser)
        }
    }

    private fun showUsers(first: User, second: User) {
        // show users on UI
    }
}

private class User {

}