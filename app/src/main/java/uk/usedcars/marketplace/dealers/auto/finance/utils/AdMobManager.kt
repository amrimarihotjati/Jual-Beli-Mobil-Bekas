package uk.usedcars.marketplace.dealers.auto.finance.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.nativead.NativeAdOptions

object AdMobManager {
    private const val TAG = "AdMobManager"
    private var interstitialAd: InterstitialAd? = null
    private var isAdLoading = false
    
    // Cache for Native Ads to prevent reloading on scroll
    private val nativeAdCache = mutableMapOf<String, NativeAd>()
    
    // Frequency capping: show every 3 interactions
    private var interactionCount = 0
    private const val INTERACTION_THRESHOLD = 3

    fun loadInterstitialAd(context: Context, adUnitId: String) {
        if (interstitialAd != null || isAdLoading) {
            return
        }

        isAdLoading = true
        val adRequest = AdRequest.Builder().build()
        
        InterstitialAd.load(
            context,
            adUnitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, "Ad failed to load: ${adError.message}")
                    interstitialAd = null
                    isAdLoading = false
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    interstitialAd = ad
                    isAdLoading = false
                }
            }
        )
    }

    fun showInterstitialAd(activity: Activity?, onAdDismissed: () -> Unit) {
        if (interstitialAd != null && activity != null) {
            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    interstitialAd = null
                    onAdDismissed()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.d(TAG, "Ad failed to show: ${adError.message}")
                    interstitialAd = null
                    onAdDismissed()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    interstitialAd = null
                }
            }
            interstitialAd?.show(activity)
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.")
            onAdDismissed()
        }
    }
    
    fun showInterstitialAdWithCounter(activity: Activity?, onAdDismissed: () -> Unit) {
        interactionCount++
        Log.d(TAG, "Interaction Count: $interactionCount / $INTERACTION_THRESHOLD")
        
        if (interactionCount >= INTERACTION_THRESHOLD) {
            interactionCount = 0 // Reset counter
            if (interstitialAd != null) {
                showInterstitialAd(activity) {
                    if (activity != null) {
                        loadInterstitialAd(activity, "ca-app-pub-3940256099942544/1033173712") // Using Test ID, replace in production
                    }
                    onAdDismissed()
                }
            } else {
                Log.d(TAG, "Ad reached threshold but is null. Loading for next time.")
                if (activity != null) {
                    loadInterstitialAd(activity, "ca-app-pub-3940256099942544/1033173712")
                }
                onAdDismissed()
            }
        } else {
            onAdDismissed()
        }
    }

    fun loadNativeAd(context: Context, adUnitId: String, onAdLoaded: (NativeAd?) -> Unit) {
        val builder = AdLoader.Builder(context, adUnitId)
            .forNativeAd { nativeAd ->
                onAdLoaded(nativeAd)
            }
            .withAdListener(object : com.google.android.gms.ads.AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, "Native ad failed to load: ${adError.message}")
                    onAdLoaded(null)
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            
        builder.build().loadAd(AdRequest.Builder().build())
    }

    fun loadNativeAdCached(context: Context, adUnitId: String, cacheKey: String, onAdLoaded: (NativeAd?) -> Unit) {
        if (nativeAdCache.containsKey(cacheKey)) {
            val cachedAd = nativeAdCache[cacheKey]
            if (cachedAd != null) {
                Log.d(TAG, "Returning cached native ad for key: $cacheKey")
                onAdLoaded(cachedAd)
                return
            }
        }
        
        Log.d(TAG, "Loading new native ad for key: $cacheKey")
        loadNativeAd(context, adUnitId) { ad ->
            if (ad != null) {
                nativeAdCache[cacheKey] = ad
            }
            onAdLoaded(ad)
        }
    }
}


fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
