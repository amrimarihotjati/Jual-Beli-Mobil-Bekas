package uk.usedcars.marketplace.dealers.auto.finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import uk.usedcars.marketplace.dealers.auto.finance.data.api.ApiService
import uk.usedcars.marketplace.dealers.auto.finance.data.repository.CarRepository
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.Marketplace
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.DetailScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.IntroScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.MainScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.MainLayoutScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.CarDetailScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.SplashScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.CreditCalculatorScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.CarViewModel
import uk.usedcars.marketplace.dealers.auto.finance.theme.JualBeliMobilBekasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configure AdMob to match Play Store 'Rated for 3+' Rating (G = General Audiences)
        val requestConfiguration = RequestConfiguration.Builder()
            .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE)
            .setTagForUnderAgeOfConsent(RequestConfiguration.TAG_FOR_UNDER_AGE_OF_CONSENT_FALSE)
            .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
            .build()
        MobileAds.setRequestConfiguration(requestConfiguration)

        // Initialize AdMob in background to avoid blocking Main Thread
        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@MainActivity) {}
            // Load App Open Ad right after MobileAds initialization is complete
            val appOpenAdManager = (application as MainApplication).appOpenAdManager
            runOnUiThread {
                appOpenAdManager.loadAd(this@MainActivity)
            }
        }

        // Add Interceptor for API Key
        val apiKeyInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", "app_2989bba1e41f427ba7194d21")
                .build()
            chain.proceed(request)
        }
        
        val client = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

        // Manual DI
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cms-app-api.amrimarihotjati.workers.dev/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val repository = CarRepository(apiService)

        setContent {
            val navController = rememberNavController()
            val viewModel: CarViewModel = viewModel(factory = object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CarViewModel(repository) as T
                }
            })
            val context = LocalContext.current

            JualBeliMobilBekasTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {
                    Box(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
                        NavHost(navController = navController, startDestination = "splash") {
                            composable("splash") {
                                SplashScreen(
                                onNavigateToIntro = {
                                    navController.navigate("intro") {
                                        popUpTo("splash") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("intro") {
                            IntroScreen(
                                onFinishIntro = {
                                    navController.navigate("main_layout") {
                                        popUpTo("intro") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("main_layout") {
                            MainLayoutScreen(
                                viewModel = viewModel,
                                onNavigateToDetail = { marketplace ->
                                    viewModel.selectedMarketplace = marketplace
                                    navController.navigate("detail")
                                },
                                onNavigateToCarDetail = { car ->
                                    // Normally pass to viewModel, let's just add it dynamically or store in viewModel
                                    viewModel.selectedCar = car
                                    navController.navigate("car_detail")
                                },
                                onNavigateToArticleDetail = { articleId ->
                                    navController.navigate("article_detail/$articleId")
                                }
                            )
                        }
                        composable("detail") {
                            viewModel.selectedMarketplace?.let { marketplace ->
                                DetailScreen(
                                    marketplace = marketplace,
                                    onBack = { navController.popBackStack() }
                                )
                            }
                        }
                        composable("car_detail") {
                            val state = viewModel.uiState.collectAsState().value
                            val favorites by viewModel.favorites.collectAsState()
                            viewModel.selectedCar?.let { car ->
                                if (state is uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.UiState.Success) {
                                    CarDetailScreen(
                                        car = car,
                                        config = state.config,
                                        isFavorite = favorites.contains(car.id),
                                        onFavoriteToggle = { viewModel.toggleFavorite(context, car.id) },
                                        onBack = { navController.popBackStack() }
                                    )
                                }
                            }
                        }
                        composable(
                            "article_detail/{articleId}",
                            arguments = listOf(navArgument("articleId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val articleId = backStackEntry.arguments?.getString("articleId") ?: ""
                            uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.ArticleDetailScreen(
                                articleId = articleId,
                                onBack = { navController.popBackStack() }
                            )
                        }
                        }
                    }
                }
            }
        }
    }
}
