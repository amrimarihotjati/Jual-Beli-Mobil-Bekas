package uk.usedcars.marketplace.dealers.auto.finance.domain.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AppConfig(
    val version: String,
    val updateUrl: String,
    @SerializedName("admob_config") val admobConfig: AdMobConfig,
    @SerializedName("slideshow") val slideshow: List<SlideshowItem>,
    @SerializedName("marketplaces") val marketplaces: List<Marketplace>,
    @SerializedName("used_cars") val usedCars: List<UsedCar> = emptyList()
)

@Keep
data class UsedCar(
    @SerializedName("id") val id: String,
    @SerializedName("brand") val brand: String,
    @SerializedName("model") val model: String,
    @SerializedName("description") val description: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("price_history") val priceHistoryRaw: String?,
    
    // Legacy fields (kept as optional to avoid breaking other parts of the app during migration, if any are still accessed implicitly)
    val name: String? = "",
    val year: String? = "",
    @SerializedName("price_range") val priceRange: String? = "",
    @SerializedName("image_urls") val imageUrls: List<String>? = emptyList(),
    val tags: List<String>? = emptyList(),
    val transmission: String? = "Automatic (AT)",
    @SerializedName("fuel_type") val fuelType: String? = "Bensin",
    val mileage: String? = "50,000 km",
    val location: String? = "Jakarta",
    val seats: String? = "5 Seater",
    val variants: List<CarVariant>? = emptyList()
) {
    fun getPriceHistoryMap(): Map<String, String> {
        if (priceHistoryRaw.isNullOrEmpty()) return emptyMap()
        return try {
            val type = object : com.google.gson.reflect.TypeToken<Map<String, String>>() {}.type
            com.google.gson.Gson().fromJson(priceHistoryRaw, type)
        } catch (e: Exception) {
            emptyMap()
        }
    }
}

@Keep
data class CarVariant(
    val name: String,
    val price: String,
    val level: String // e.g., "Terendah", "Menengah", "Tertinggi"
)

@Keep
data class AdMobConfig(
    @SerializedName("native_id") val nativeId: String,
    @SerializedName("interstitial_id") val interstitialId: String,
    @SerializedName("interstitial_interval") val interstitialInterval: Int,
    @SerializedName("native_freq") val nativeFreq: Int = 4,
    @SerializedName("open_ad_id") val openAdId: String = ""
)

@Keep
data class SlideshowItem(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image_url") val imageUrl: String
)

@Keep
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

@Keep
data class ApiResponse<T>(
    val success: Boolean,
    val data: T
)
