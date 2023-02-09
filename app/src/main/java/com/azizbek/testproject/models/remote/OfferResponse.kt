package com.azizbek.testproject.models.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OfferResponse(
    @Json(name = "offers")
    val offers: List<Offer?>?
) {
    @JsonClass(generateAdapter = true)
    data class Offer(
        @Json(name = "attributes")
        val attributes: List<Attribute?>?,
        @Json(name = "brand")
        val brand: String?,
        @Json(name = "category")
        val category: String?,
        @Json(name = "id")
        val id: Long?,
        @Json(name = "image")
        val image: Image?,
        @Json(name = "merchant")
        val merchant: String?,
        @Json(name = "name")
        val name: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Attribute(
            @Json(name = "name")
            val name: String,
            @Json(name = "value")
            val value: String
        )

        @JsonClass(generateAdapter = true)
        data class Image(
            @Json(name = "height")
            val height: String?,
            @Json(name = "url")
            val url: String?,
            @Json(name = "width")
            val width: String?
        )
    }
}