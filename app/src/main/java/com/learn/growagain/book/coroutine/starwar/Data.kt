package com.learn.growagain.book.coroutine.starwar

import kotlinx.coroutines.delay


class CharacterRemoteServices {

    suspend fun fetchAllCharacters(): List<Character> {
        delay(3000)
        return ALL_CHARACTERS
    }
}


class CharacterRemoteRepository(
    private val characterRemoteServices: CharacterRemoteServices
) {
    suspend fun fetchAllCharacters(): List<Character> {
        return characterRemoteServices.fetchAllCharacters()
    }
}