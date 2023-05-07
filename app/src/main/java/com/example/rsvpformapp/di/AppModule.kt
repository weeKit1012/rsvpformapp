package com.example.rsvpformapp.di

import com.example.rsvpformapp.data.remote.FormApi
import com.example.rsvpformapp.data.repository.FormRepositoryImpl
import com.example.rsvpformapp.domain.repository.FormRepository
import com.example.rsvpformapp.domain.use_case.SendRsvpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient
    {
        return OkHttpClient.Builder()
            .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideFormApi(
            client: OkHttpClient
    ): FormApi
    {
        return Retrofit.Builder()
            .baseUrl(FormApi.BASE_URL)
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFormRepository(
            api: FormApi
    ): FormRepository
    {
        return FormRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSendRsvpUseCase(
            repository: FormRepository
    ): SendRsvpUseCase
    {
        return SendRsvpUseCase(repository)
    }
}