package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import android.app.Activity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import uk.usedcars.marketplace.dealers.auto.finance.R
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.Marketplace
import uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.CarViewModel
import uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.UiState
import uk.usedcars.marketplace.dealers.auto.finance.utils.AdMobManager

@Composable
fun MainScreen(
    viewModel: CarViewModel,
    onNavigateToDetail: (Marketplace) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val activity = context as? Activity

    when (val state = uiState) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.message}", color = Color.Red)
            }
        }
        is UiState.Success -> {
            // Load interstitial so it's ready when clicked
            LaunchedEffect(Unit) {
                AdMobManager.loadInterstitialAd(context, state.config.admobConfig.interstitialId)
            }
            
            MainContent(
                config = state.config,
                onMarketplaceClick = { marketplace ->
                    if (viewModel.onMarketplaceClicked() && activity != null) {
                        AdMobManager.showInterstitialAd(activity) {
                            onNavigateToDetail(marketplace)
                            AdMobManager.loadInterstitialAd(context, state.config.admobConfig.interstitialId)
                        }
                    } else {
                        onNavigateToDetail(marketplace)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(config: AppConfig, onMarketplaceClick: (Marketplace) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hai, Selamat Datang!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Top Section: Slideshow
        val pagerState = rememberPagerState(pageCount = { config.slideshow.size })
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            val item = config.slideshow[page]
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = item.title,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Middle Section: Native Ad
        NativeAdPlaceholder(config.admobConfig.nativeId)

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Section: Grid of Marketplaces
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(config.marketplaces) { marketplace ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { onMarketplaceClick(marketplace) },
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = marketplace.logoUrl,
                            contentDescription = marketplace.name,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(bottom = 8.dp),
                            contentScale = ContentScale.Fit
                        )
                        Text(
                            text = marketplace.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NativeAdPlaceholder(adUnitId: String) {
    var nativeAd by remember { mutableStateOf<NativeAd?>(null) }
    val context = LocalContext.current

    LaunchedEffect(adUnitId) {
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                nativeAd = ad
            }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }

    if (nativeAd != null) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            factory = { ctx ->
                val adView = LayoutInflater.from(ctx).inflate(R.layout.native_ad_layout, null) as NativeAdView
                
                adView.headlineView = adView.findViewById<TextView>(R.id.ad_headline)
                adView.bodyView = adView.findViewById<TextView>(R.id.ad_body)
                adView.callToActionView = adView.findViewById<Button>(R.id.ad_call_to_action)

                (adView.headlineView as TextView).text = nativeAd?.headline
                (adView.bodyView as TextView).text = nativeAd?.body
                (adView.callToActionView as Button).text = nativeAd?.callToAction

                adView.setNativeAd(nativeAd!!)
                adView
            }
        )
    } else {
        // Shimmer or placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading Ad...", color = Color.Gray)
        }
    }
}
