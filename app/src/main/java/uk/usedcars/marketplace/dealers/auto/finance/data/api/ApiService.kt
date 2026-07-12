package uk.usedcars.marketplace.dealers.auto.finance.data.api

import retrofit2.http.GET
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig

interface ApiService {
    @GET("mobil-bekas/config.json")
    suspend fun getConfig(): AppConfig
}
