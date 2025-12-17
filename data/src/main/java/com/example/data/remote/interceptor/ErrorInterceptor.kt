package com.example.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            400 -> {
                //Show Bad Request Error Message
            }
            401 -> {
                //Show UnauthorizedError Message
            }
            403 -> {
                //Show Forbidden Message
            }
            404 -> {
                //Show NotFound Message
            }
            // ... and so on
        }
        return response
    }
}