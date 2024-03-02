package com.raazi.shoppinglist

data class LocationData(
    val latitute : Double,
    val longitute: Double
)

data class GeocodingResponse(
        val results: List<GeocodingResult>,
        val status: String
)

data class GeocodingResult(
    val formatted_address: String
)