package com.example.sakhcast.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.sakhcast.BASE_URL
import com.example.sakhcast.SHARED_PREFS_TOKEN_KEY
import com.example.sakhcast.data.api_service.SakhCastApiService
import com.example.sakhcast.data.downloader.AndroidDownloader
import com.example.sakhcast.data.downloader.Downloader
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SakhCastModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile("preferences") }
        )
    }

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    fun provideHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()
            val token = sharedPreferences.getString(SHARED_PREFS_TOKEN_KEY, "")
            Log.i("!!!", "token sp = $token")
            val requestBuilder = original.newBuilder()
                .header("Authorization", value = token.toString())
                .header("X-Force-Code", "1")
                .header("X-App-Id", "4")
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
        return retrofit
    }

    @Provides
    fun provideSakhCastApiService(retrofit: Retrofit): SakhCastApiService =
        retrofit.create(SakhCastApiService::class.java)

    @Provides
    fun provideExoPlayer(@ApplicationContext context: Context): Player {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    @Singleton
    fun provideDownloader(@ApplicationContext context: Context): Downloader {
        return AndroidDownloader(context)
    }

}