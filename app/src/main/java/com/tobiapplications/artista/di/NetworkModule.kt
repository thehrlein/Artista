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
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = Kodein.Module("networkModule") {

    bind() from singleton { Gson() }
    bind() from singleton { OkHttpClient() }
    bind() from singleton { HttpLoggingInterceptor() }
    bind() from singleton { RequestInterceptor() }
    bind() from  singleton {
        val timeOut = 30L
        val clientBuilder = instance<OkHttpClient>().newBuilder()
        clientBuilder.readTimeout(timeOut, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS)
        instance<HttpLoggingInterceptor>().level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(instance<HttpLoggingInterceptor>())
        clientBuilder.addInterceptor(instance<RequestInterceptor>())

        Retrofit.Builder().client(clientBuilder.build())
            .baseUrl(ArtistaUrls.BASE_LAST_FM_URL)
            .addConverterFactory(GsonConverterFactory.create(instance()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    bind() from singleton { instance<Retrofit>().create(ArtistaApi::class.java) }
    bind() from singleton { NetworkManager(instance()) }
}

