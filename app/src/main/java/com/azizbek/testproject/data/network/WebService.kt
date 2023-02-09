package com.azizbek.testproject.data.network

import com.azizbek.testproject.data.models.OfferResponse
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