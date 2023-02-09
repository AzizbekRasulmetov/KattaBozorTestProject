package com.azizbek.testproject.domain.use_case

import com.azizbek.testproject.data.repository.Repository
import javax.inject.Inject

class GetMobileOffersUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getOffers()
}