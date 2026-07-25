package uk.usedcars.marketplace.dealers.auto.finance.data.api

import retrofit2.http.GET
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.ApiResponse
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.UsedCar
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.Marketplace
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.SlideshowItem
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AdMobConfig

interface ApiService {
    @GET("used_cars")
    suspend fun getUsedCars(): ApiResponse<List<UsedCar>>

    @GET("marketplaces")
    suspend fun getMarketplaces(): ApiResponse<List<Marketplace>>

    @GET("slideshow")
    suspend fun getSlideshow(): ApiResponse<List<SlideshowItem>>

    @GET("admob_config")
    suspend fun getAdmobConfig(): ApiResponse<List<AdMobConfig>>
}
