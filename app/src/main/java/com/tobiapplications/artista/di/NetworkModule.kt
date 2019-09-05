package com.tobiapplications.artista.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tobiapplications.artista.utils.general.ArtistaConstants
import com.tobiapplications.artista.utils.general.ArtistaUrls
import com.tobiapplications.artista.utils.network.ArtistaApi
import com.tobiapplications.artista.utils.network.NetworkManager
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import com.tobiapplications.artista.utils.network.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { Gson() }
    single { OkHttpClient() }
    single { HttpLoggingInterceptor() }
    single { RequestInterceptor() }
    single {
        val timeOut = 30L
        val clientBuilder = get<OkHttpClient>().newBuilder()
        clientBuilder.readTimeout(timeOut, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS)
        get<HttpLoggingInterceptor>().level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(get<HttpLoggingInterceptor>())
        clientBuilder.addInterceptor(get<RequestInterceptor>())

        Retrofit.Builder().client(clientBuilder.build())
            .baseUrl(ArtistaUrls.BASE_LAST_FM_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single { get<Retrofit>().create(ArtistaApi::class.java) }
    single<NetworkManagerDelegate> { NetworkManager(get()) }
}

