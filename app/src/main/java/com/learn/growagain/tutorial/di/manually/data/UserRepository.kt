package com.learn.growagain.tutorial.di.manually.data

class UserRepository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

}