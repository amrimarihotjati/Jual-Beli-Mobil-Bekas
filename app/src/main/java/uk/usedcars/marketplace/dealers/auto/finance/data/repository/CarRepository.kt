package uk.usedcars.marketplace.dealers.auto.finance.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import uk.usedcars.marketplace.dealers.auto.finance.data.api.ApiService
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AdMobConfig

class CarRepository(private val apiService: ApiService) {
    suspend fun getConfig(): Result<AppConfig> {
        return withContext(Dispatchers.IO) {
            try {
                val usedCarsDeferred = async { apiService.getUsedCars() }
                val marketplacesDeferred = async { apiService.getMarketplaces() }
                val slideshowDeferred = async { apiService.getSlideshow() }
                val admobConfigDeferred = async { apiService.getAdmobConfig() }

                val usedCars = usedCarsDeferred.await().data
                val marketplaces = marketplacesDeferred.await().data
                val slideshow = slideshowDeferred.await().data
                val admobConfigList = admobConfigDeferred.await().data

                val admobConfig = admobConfigList.firstOrNull() ?: AdMobConfig("", "", 0, 4)

                val appConfig = AppConfig(
                    version = "1.0",
                    updateUrl = "",
                    admobConfig = admobConfig,
                    slideshow = slideshow,
                    marketplaces = marketplaces,
                    usedCars = usedCars
                )
                
                Result.success(appConfig)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
