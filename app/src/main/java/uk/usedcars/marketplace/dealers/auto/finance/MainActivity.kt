package uk.usedcars.marketplace.dealers.auto.finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.MobileAds
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.usedcars.marketplace.dealers.auto.finance.data.api.ApiService
import uk.usedcars.marketplace.dealers.auto.finance.data.repository.CarRepository
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.Marketplace
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.DetailScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.IntroScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.MainScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.SplashScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.CreditCalculatorScreen
import uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.CarViewModel
import uk.usedcars.marketplace.dealers.auto.finance.theme.JualBeliMobilBekasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize AdMob
        MobileAds.initialize(this) {}

        // Manual DI
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val repository = CarRepository(apiService)

        setContent {
            val navController = rememberNavController()
            val viewModel: CarViewModel = viewModel(factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CarViewModel(repository) as T
                }
            })

            JualBeliMobilBekasTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {
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
                                    navController.navigate("main") {
                                        popUpTo("intro") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("main") {
                            MainScreen(
                                viewModel = viewModel,
                                onNavigateToDetail = { marketplace ->
                                    viewModel.selectedMarketplace = marketplace
                                    navController.navigate("detail")
                                },
                                onNavigateToCalculator = {
                                    navController.navigate("calculator")
                                }
                            )
                        }
                        composable("calculator") {
                            CreditCalculatorScreen(
                                onBack = { navController.popBackStack() }
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
                    }
                }
            }
        }
    }
}
