package com.dranoer.photoalbum.di

import com.dranoer.photoalbum.data.remote.WebService
import com.dranoer.photoalbum.domain.DomainModelMapper
import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.util.Constant.BASE_URL
import com.dranoer.photoalbum.util.Constant.NETWORK_REQUEST_TIMEOUT
import com.dranoer.photoalbum.util.UiModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideWebService(retrofit: Retrofit): WebService =
        retrofit.create(WebService::class.java)

    @Provides
    fun provideDomainMapper(): DomainModelMapper = DomainModelMapper()

    @Provides
    fun provideUiMapper(): UiModelMapper = UiModelMapper()

    @Provides
    fun provideRepository(webService: WebService, mapper: DomainModelMapper): PhotoRepository =
        PhotoRepository(webService = webService, domainMapper = mapper)
}