package com.learn.growagain.codelab.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.growagain.codelab.coroutine.util.singleArgViewModelFactory

class TitleViewModel(
    private val titleRepository: TitleRepository
) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::TitleViewModel)
    }

    private var totalCount = 0

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message


}