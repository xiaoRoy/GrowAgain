package com.learn.growagain.codelab.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.learn.growagain.codelab.coroutine.util.BACKGROUND

class TitleRepository(
    val titleNetwork: TitleNetwork,
    val titleDao: TitleDao
) {

    val title: LiveData<String?> = titleDao.title.map { it?.title }

    fun refreshTitle(titleRefreshCallback: TitleRefreshCallback) {
        BACKGROUND.submit {
            try {
                val response = titleNetwork.fetchNextTitle().execute()
                if(response.isSuccessful) {
                    val title: String = response.body() ?: throw IllegalStateException()
                    titleDao.insertTitle(Title(title))
                    titleRefreshCallback.onCompleted()
                } else {
                    titleRefreshCallback.onError(TitleRefreshError("Unable to refresh title", null))
                }
            }catch (cause: Throwable) {
                titleRefreshCallback.onError(TitleRefreshError("", cause))
            }
        }
    }
}

class TitleRefreshError(message: String, cause: Throwable?) : Throwable(message, cause)

interface TitleRefreshCallback {
    fun onCompleted()
    fun onError(cause: Throwable)
}