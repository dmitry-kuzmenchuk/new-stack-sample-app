package ru.dmitry.kuzmenchuk.newstackapp.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class UnsplashRequestInterceptor(
    private val apiToken: String,
    private val apiVersion: Int
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val updatedRequest = chain.request().newBuilder()
            .addHeader("Authorization", apiToken)
            .addHeader("Accept-Version", "v$apiVersion")
            .build()
        return chain.proceed(updatedRequest)
    }
}