package uk.usedcars.marketplace.dealers.auto.finance.data.api

import retrofit2.http.GET
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig

interface ApiService {
    @GET("amrimarihotjati/Jual-Beli-Mobil-Bekas/main/config.json")
    suspend fun getConfig(): AppConfig
}
