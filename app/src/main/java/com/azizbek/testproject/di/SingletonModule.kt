package com.azizbek.testproject.di

import com.azizbek.testproject.data.network.WebService
import com.azizbek.testproject.createMoshi
import com.azizbek.testproject.createRetrofitWithMoshi
import com.azizbek.testproject.data.repository.Repository
import com.azizbek.testproject.domain.repository.RepositoryImpl
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

    @Provides
    fun provideRepository(webService: WebService): Repository {
        return RepositoryImpl(webService)
    }

}


