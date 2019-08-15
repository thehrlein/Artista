package com.tobiapplications.artista.di.module

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tobiapplications.artista.utils.general.ArtistaUrls
import com.tobiapplications.artista.utils.network.ArtistaAPi
import com.tobiapplications.artista.utils.network.NetworkManager
import com.tobiapplications.artista.utils.network.NetworkManagerDelegate
import com.tobiapplications.artista.utils.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, httpLoggingInterceptor: HttpLoggingInterceptor,
                       okHttpClient: OkHttpClient, requestInterceptor: RequestInterceptor) : Retrofit {

        val timeOut = 30L
        val clientBuilder = okHttpClient.newBuilder()
        clientBuilder.readTimeout(timeOut, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(requestInterceptor)
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder().client(clientBuilder.build())
            .baseUrl(ArtistaUrls.BASE_LAST_FM_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : ArtistaAPi {
        return retrofit.create(ArtistaAPi::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkManager(api: ArtistaAPi) : NetworkManagerDelegate {
        return NetworkManager(api)
    }
}

