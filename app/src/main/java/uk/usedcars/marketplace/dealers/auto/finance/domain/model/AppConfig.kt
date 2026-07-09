package uk.usedcars.marketplace.dealers.auto.finance.domain.model

import com.google.gson.annotations.SerializedName

data class AppConfig(
    @SerializedName("admob_config") val admobConfig: AdMobConfig,
    @SerializedName("slideshow") val slideshow: List<SlideshowItem>,
    @SerializedName("marketplaces") val marketplaces: List<Marketplace>
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
    val features: List<String> = emptyList()
)
