package com.learn.growagain.book.coroutine.starwar

class Character(
    private val id: String,
    val name: String
)

val ALL_CHARACTERS: List<Character>
    get() {
        return (1..44).map { index ->
            val name = "name:#$index"
            Character(index.toString(), name)
        }.toList()
    }