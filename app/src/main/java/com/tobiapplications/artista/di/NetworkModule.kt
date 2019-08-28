package com.tobiapplications.artista.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tobiapplications.artista.utils.general.ArtistaUrls
import com.tobiapplications.artista.utils.network.ArtistaApi
import com.tobiapplications.artista.utils.network.NetworkManager
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import com.tobiapplications.artista.utils.network.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.multiton
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = Kodein.Module {

    bind<Gson>() with singleton { Gson() }
    bind<OkHttpClient>() with singleton { OkHttpClient() }
    bind<HttpLoggingInterceptor>() with singleton { HttpLoggingInterceptor() }
    bind<RequestInterceptor>() with singleton { RequestInterceptor() }
    bind<Retrofit>() with multiton {
        gson: Gson,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        okHttpClient: OkHttpClient,
        requestInterceptor: RequestInterceptor ->

        val timeOut = 30L
        val clientBuilder = okHttpClient.newBuilder()
        clientBuilder.readTimeout(timeOut, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(requestInterceptor)
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        Retrofit.Builder().client(clientBuilder.build())
            .baseUrl(ArtistaUrls.BASE_LAST_FM_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    bind<ArtistaApi>() with multiton { retrofit: Retrofit -> retrofit.create(ArtistaApi::class.java) }
    bind<NetworkManagerDelegate>() with multiton { api: ArtistaApi -> NetworkManager(api) }
}

