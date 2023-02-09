package com.azizbek.testproject.domain.models

import com.azizbek.testproject.data.models.OfferResponse

data class PhoneOffer(
    val name: String,
    val brand: String,
    val category: String,
    val merchant: String,
    val imageUrl: String?,
    val attributes: List<Pair<String, String>>
)


fun List<OfferResponse.Offer>.toPhoneOffers() = this.map { it.toPhoneOffer() }

fun OfferResponse.Offer.toPhoneOffer(): PhoneOffer {
    return PhoneOffer(
        name = this.name ?: "",
        brand = this.brand ?: "",
        category = this.category ?: "",
        merchant = this.merchant ?: "",
        imageUrl = this.image?.url,
        attributes = this.attributes?.filterNotNull()?.map { it.name to it.value } ?: emptyList()
    )
}