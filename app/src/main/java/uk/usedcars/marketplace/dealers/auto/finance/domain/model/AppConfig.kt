package uk.usedcars.marketplace.dealers.auto.finance.domain.model

import com.google.gson.annotations.SerializedName

data class AppConfig(
    val version: String,
    val updateUrl: String,
    @SerializedName("admob_config") val admobConfig: AdMobConfig,
    @SerializedName("slideshow") val slideshow: List<SlideshowItem>,
    @SerializedName("marketplaces") val marketplaces: List<Marketplace>,
    @SerializedName("used_cars") val usedCars: List<UsedCar> = emptyList()
)

data class UsedCar(
    val id: String,
    val name: String,
    val brand: String,
    val year: String,
    @SerializedName("price_range") val priceRange: String,
    @SerializedName("image_urls") val imageUrls: List<String> = emptyList(),
    val description: String,
    val tags: List<String> = emptyList(),
    val transmission: String = "Automatic (AT)",
    @SerializedName("fuel_type") val fuelType: String = "Bensin",
    val mileage: String = "50,000 km",
    val location: String = "Jakarta"
)

data class AdMobConfig(
    @SerializedName("native_id") val nativeId: String,
    @SerializedName("interstitial_id") val interstitialId: String,
    @SerializedName("interstitial_interval") val interstitialInterval: Int
)

data class SlideshowItem(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image_url") val imageUrl: String
)

data class Marketplace(
    val id: Int,
    val name: String,
    @SerializedName("logo_url") val logoUrl: String,
    val description: String,
    @SerializedName("direct_link") val directLink: String,
    val rating: Double = 0.0,
    @SerializedName("total_cars") val totalCars: String = "",
    @SerializedName("promo_text") val promoText: String = "",
    val features: List<String> = emptyList(),
    val services: List<String> = emptyList(),
    @SerializedName("operational_hours") val operationalHours: String = "",
    @SerializedName("established_year") val establishedYear: String = "",
    val headquarters: String = "",
    val tags: List<String> = emptyList(),
    @SerializedName("payment_methods") val paymentMethods: List<String> = emptyList()
)
