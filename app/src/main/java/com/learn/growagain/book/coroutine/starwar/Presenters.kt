package com.learn.growagain.book.coroutine.starwar

interface CharacterListPresenter {
    fun viewAllCharacters()
}

interface CharacterListViewRenderer {
    fun showAllCharacters(characterList: List<Character>)

    fun showLoading()

    fun hideLoading()
}

class CharacterListPresenterImpl(
    private val characterViewRenderer: CharacterListViewRenderer,
    private val characterRemoteRepository: CharacterRemoteRepository
): CharacterListPresenter{



    override fun viewAllCharacters() {
    }
}