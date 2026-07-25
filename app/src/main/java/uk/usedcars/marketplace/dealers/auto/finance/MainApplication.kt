package uk.usedcars.marketplace.dealers.auto.finance

import android.app.Application
import uk.usedcars.marketplace.dealers.auto.finance.utils.AppOpenAdManager

class MainApplication : Application() {
    lateinit var appOpenAdManager: AppOpenAdManager
        private set

    override fun onCreate() {
        super.onCreate()
        
        // Initialize App Open Ad Manager to handle app foreground lifecycle
        appOpenAdManager = AppOpenAdManager(this)
        
        // Note: Initial loadAd will happen after MobileAds is initialized in MainActivity
    }
}
