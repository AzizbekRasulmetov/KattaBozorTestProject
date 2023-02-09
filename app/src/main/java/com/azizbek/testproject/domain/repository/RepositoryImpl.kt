package com.azizbek.testproject.domain.repository

import com.azizbek.testproject.data.network.NetworkResult
import com.azizbek.testproject.data.network.WebService
import com.azizbek.testproject.data.repository.Repository
import com.azizbek.testproject.domain.models.PhoneOffer
import com.azizbek.testproject.domain.models.toPhoneOffers
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl @Inject constructor(private val webService: WebService) : Repository {

    override suspend fun getOffers(): NetworkResult<List<PhoneOffer>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = webService.getOffers()
                NetworkResult.Success(response.offers?.filterNotNull()?.toPhoneOffers() ?: emptyList())
            } catch (error: Exception) {
                NetworkResult.Error(error.message)
            }
        }
    }
}