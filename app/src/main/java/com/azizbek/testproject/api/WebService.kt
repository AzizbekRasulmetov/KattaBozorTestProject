package com.azizbek.testproject.api

import com.azizbek.testproject.models.remote.OfferResponse
import retrofit2.http.GET

interface WebService {

    @GET(OFFERS)
    suspend fun getOffers(): OfferResponse

    companion object {
        const val BASE_URL = "https://www.kattabozor.uz/"
        private const val RELATIVE_URL = "hh/test/api/v1/"
        const val OFFERS = "${RELATIVE_URL}offers"
    }

}