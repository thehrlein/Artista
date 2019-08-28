package com.tobiapplications.artista.utils.network

import com.tobiapplications.artista.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_FORMAT = "format"
        private const val OUTPUT_FORMAT = "json"
        private const val API_KEY = BuildConfig.LAST_FM_API_KEY
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        val originalUrl = original.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
            .addQueryParameter(QUERY_PARAM_FORMAT, OUTPUT_FORMAT)
            .build()
        requestBuilder.url(url)
        return chain.proceed(requestBuilder.build())
    }
}