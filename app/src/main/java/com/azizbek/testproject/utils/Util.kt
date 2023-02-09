package com.azizbek.testproject

import com.azizbek.testproject.data.network.WebService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

inline fun <reified T> createRetrofitWithMoshi(
    moshi: Moshi
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(WebService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    return retrofit.create(T::class.java)
}

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}