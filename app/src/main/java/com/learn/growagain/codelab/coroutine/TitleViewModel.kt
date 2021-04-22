package com.learn.growagain.codelab.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.growagain.codelab.coroutine.util.BACKGROUND
import com.learn.growagain.codelab.coroutine.util.singleArgViewModelFactory

class TitleViewModel(
    private val titleRepository: TitleRepository
) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::TitleViewModel)
    }

    private var totalCount = 0
    private var tapCount = 0

    private val tapsInString: String
        get() = "$tapCount taps"

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    fun onMessageShown() {
        _message.value = null
    }

    val title = titleRepository.title

    private val _isLoading = MutableLiveData<Boolean>(false)

    val isLoading: LiveData<Boolean> = _isLoading

    private val _taps = MutableLiveData<String>(tapsInString)

    val taps: LiveData<String> = _taps

    fun updateTitle() {

    }

    private fun updateTaps() {
        tapCount++
        BACKGROUND.submit {
            Thread.sleep(1_000)
            _taps.postValue(tapsInString)
        }
    }


}