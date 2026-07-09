import re

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/domain/model/AppConfig.kt', 'r') as f:
    content = f.read()

# Fix AppConfig class definition
new_appconfig = """data class AppConfig(
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
    @SerializedName("image_url") val imageUrl: String,
    val description: String,
    val tags: List<String> = emptyList()
)

data class AdMobConfig("""

content = re.sub(r'data class AppConfig\(.*?data class AdMobConfig\(', new_appconfig, content, flags=re.DOTALL)

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/domain/model/AppConfig.kt', 'w') as f:
    f.write(content)
