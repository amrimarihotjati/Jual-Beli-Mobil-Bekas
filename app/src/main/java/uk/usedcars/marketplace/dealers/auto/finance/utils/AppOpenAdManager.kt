package uk.usedcars.marketplace.dealers.auto.finance.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import uk.usedcars.marketplace.dealers.auto.finance.BuildConfig
import java.util.Date

/**
 * Handles App Open Ads lifecycle, loading, and caching for optimal fill rate and instant display.
 */
class AppOpenAdManager(private val application: Application) : Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    private var isShowingAd = false
    private var loadTime: Long = 0
    private var currentActivity: Activity? = null

    init {
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    private fun getAdUnitId(): String {
        val configuredId = AdMobManager.adMobConfig?.openAdId
        return if (BuildConfig.DEBUG || configuredId.isNullOrEmpty()) {
            "ca-app-pub-3940256099942544/9257395921" // Test ID
        } else {
            configuredId
        }
    }

    private fun getOptimizedAdRequest(): AdRequest {
        return AdRequest.Builder()
            .addKeyword("kredit mobil")
            .addKeyword("asuransi kendaraan")
            .addKeyword("finance")
            .addKeyword("otomotif")
            .addKeyword("mobil bekas")
            .addKeyword("trading")
            .addKeyword("investasi")
            .addKeyword("pinjaman online")
            .addKeyword("kartu kredit")
            .build()
    }

    fun loadAd(context: Context) {
        if (isLoadingAd || isAdAvailable()) {
            return
        }

        isLoadingAd = true
        val request = getOptimizedAdRequest()
        
        AppOpenAd.load(
            context,
            getAdUnitId(),
            request,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    isLoadingAd = false
                    loadTime = Date().time
                    Log.d(LOG_TAG, "App Open Ad loaded.")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    isLoadingAd = false
                    Log.d(LOG_TAG, "App Open Ad failed to load: ${loadAdError.message}")
                }
            }
        )
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    fun showAdIfAvailable(activity: Activity) {
        showAdIfAvailable(activity) {
            // Empty callback
        }
    }

    fun showAdIfAvailable(activity: Activity, onShowAdCompleteListener: () -> Unit) {
        if (isShowingAd) {
            Log.d(LOG_TAG, "The app open ad is already showing.")
            return
        }

        if (!isAdAvailable()) {
            Log.d(LOG_TAG, "The app open ad is not ready yet.")
            onShowAdCompleteListener()
            loadAd(activity)
            return
        }

        Log.d(LOG_TAG, "Will show app open ad.")
        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                appOpenAd = null
                isShowingAd = false
                Log.d(LOG_TAG, "App open ad dismissed.")
                onShowAdCompleteListener()
                // Proactively preload the next ad for better fill rate
                loadAd(activity)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                appOpenAd = null
                isShowingAd = false
                Log.d(LOG_TAG, "App open ad failed to show.")
                onShowAdCompleteListener()
                loadAd(activity)
            }

            override fun onAdShowedFullScreenContent() {
                isShowingAd = true
                Log.d(LOG_TAG, "App open ad showed.")
            }
        }
        
        isShowingAd = true
        appOpenAd?.show(activity)
    }

    // LifecycleObserver methods
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        // Show the ad (if available) when the app moves to foreground.
        currentActivity?.let { showAdIfAvailable(it) }
    }

    // ActivityLifecycleCallbacks methods
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }

    companion object {
        private const val LOG_TAG = "AppOpenAdManager"
    }
}
