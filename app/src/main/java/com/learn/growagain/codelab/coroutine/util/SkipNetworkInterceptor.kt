package com.learn.growagain.codelab.coroutine.util

import com.google.gson.Gson
import okhttp3.*

class SkipNetworkInterceptor : Interceptor {

    private var lastResult: String = ""
    val gson = Gson()
    private var attempts = 0


    override fun intercept(chain: Interceptor.Chain): Response {
        pretendToBlockForNetworkResult()
        val request = chain.request()
        return if (wantRandomError()) makeErrorResult(request) else
            makeResult(request)
    }

    private fun pretendToBlockForNetworkResult() = Thread.sleep(900)

    private fun wantRandomError() = attempts++ % 5 == 0

    private fun makeErrorResult(request: Request): Response {
        return Response.Builder()
            .code(500)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("Bad server day")
            .body(
                ResponseBody.create(
                    MediaType.get("application/json"),
                    gson.toJson(mapOf("cause" to "not sure"))
                )
            )
            .build()
    }

    private fun makeResult(request: Request): Response {
        var nextResult = lastResult
        while (nextResult == lastResult) {
            nextResult = FAKE_RESULTS.random()
        }
        lastResult = nextResult
        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("OK")
            .body(
                ResponseBody.create(
                    MediaType.get("application/json"),
                    gson.toJson(nextResult)
                )
            )
            .build()
    }
}

private val FAKE_RESULTS = listOf(
    "Hello, coroutines!",
    "My favorite feature",
    "Async made easy",
    "Coroutines by example",
    "Check out the Advanced Coroutines code lab next!"
)