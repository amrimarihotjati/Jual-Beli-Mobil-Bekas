package uk.usedcars.marketplace.dealers.auto.finance.data.api

import retrofit2.http.GET
import retrofit2.http.Url
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.NewsResponse

interface ApiService {
    @GET("amrimarihotjati/Jual-Beli-Mobil-Bekas/main/config.json")
    suspend fun getConfig(): AppConfig

    @GET
    suspend fun getNews(@Url url: String): NewsResponse
}
