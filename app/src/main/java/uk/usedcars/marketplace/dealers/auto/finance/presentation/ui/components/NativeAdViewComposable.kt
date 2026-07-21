package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import uk.usedcars.marketplace.dealers.auto.finance.R
import uk.usedcars.marketplace.dealers.auto.finance.utils.AdMobManager

@Composable
fun NativeAdViewComposable(
    adUnitId: String = AdMobManager.adMobConfig?.nativeId ?: "ca-app-pub-3940256099942544/2247696110",
    cacheKey: String = "default_native_ad",
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val nativeAdState = remember { mutableStateOf<NativeAd?>(null) }

    LaunchedEffect(adUnitId, cacheKey) {
        AdMobManager.loadNativeAdCached(context, adUnitId, cacheKey) { ad ->
            nativeAdState.value = ad
        }
    }

    DisposableEffect(nativeAdState.value) {
        onDispose {
            // Do not destroy the ad here because it is cached!
            // AdMobManager will manage the lifecycle or we destroy it on app exit.
        }
    }

    nativeAdState.value?.let { nativeAd ->
        AndroidView(
            modifier = modifier.fillMaxWidth().padding(8.dp),
            factory = { ctx ->
                val adView = LayoutInflater.from(ctx)
                    .inflate(R.layout.native_ad_layout, null) as NativeAdView

                // Register Views
                adView.headlineView = adView.findViewById<TextView>(R.id.ad_headline)
                adView.bodyView = adView.findViewById<TextView>(R.id.ad_body)
                adView.callToActionView = adView.findViewById<Button>(R.id.ad_call_to_action)

                // Populate Views
                (adView.headlineView as TextView).text = nativeAd.headline
                
                if (nativeAd.body == null) {
                    adView.bodyView?.visibility = View.INVISIBLE
                } else {
                    adView.bodyView?.visibility = View.VISIBLE
                    (adView.bodyView as TextView).text = nativeAd.body
                }
                
                if (nativeAd.callToAction == null) {
                    adView.callToActionView?.visibility = View.INVISIBLE
                } else {
                    adView.callToActionView?.visibility = View.VISIBLE
                    (adView.callToActionView as Button).text = nativeAd.callToAction
                }

                adView.setNativeAd(nativeAd)
                adView
            }
        )
    }
}
