package uk.usedcars.marketplace.dealers.auto.finance.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uk.usedcars.marketplace.dealers.auto.finance.data.api.ApiService
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig

class CarRepository(private val apiService: ApiService) {
    suspend fun getConfig(): Result<AppConfig> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getConfig()
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getNews(): Result<uk.usedcars.marketplace.dealers.auto.finance.domain.model.NewsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getNews("https://api.rss2json.com/v1/api.json?rss_url=https://www.antaranews.com/rss/otomotif.xml")
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
