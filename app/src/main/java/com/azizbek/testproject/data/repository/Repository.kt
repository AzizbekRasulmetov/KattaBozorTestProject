package com.azizbek.testproject.data.repository

import com.azizbek.testproject.data.network.NetworkResult
import com.azizbek.testproject.domain.models.PhoneOffer

interface Repository {

    suspend fun getOffers(): NetworkResult<List<PhoneOffer>>

}