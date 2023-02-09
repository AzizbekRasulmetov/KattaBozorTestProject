package com.azizbek.testproject.di

import com.azizbek.testproject.api.WebService
import com.azizbek.testproject.createMoshi
import com.azizbek.testproject.createRetrofitWithMoshi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun provideMoshi(): Moshi = createMoshi()

    @Provides
    fun provideWebService(moshi: Moshi): WebService = createRetrofitWithMoshi(moshi)
}


