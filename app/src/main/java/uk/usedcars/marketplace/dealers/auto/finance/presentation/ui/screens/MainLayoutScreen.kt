package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.Marketplace
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.UsedCar
import uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.CarViewModel
import uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.UiState
import uk.usedcars.marketplace.dealers.auto.finance.utils.AdMobManager
import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect

@Composable
fun MainLayoutScreen(
    viewModel: CarViewModel,
    onNavigateToDetail: (Marketplace) -> Unit,
    onNavigateToCarDetail: (UsedCar) -> Unit
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    
    LaunchedEffect(Unit) {
        viewModel.loadFavorites(context)
    }
    
    val items = listOf(
        BottomNavItem.CarPrices,
        BottomNavItem.Home,
        BottomNavItem.Calculator
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) { saveState = true }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.CarPrices.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) {
                MainScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { marketplace ->
                        AdMobManager.showInterstitialAdWithCounter(context as Activity) {
                            onNavigateToDetail(marketplace)
                        }
                    },
                    onNavigateToCalculator = {
                        navController.navigate(BottomNavItem.Calculator.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(BottomNavItem.Calculator.route) {
                CreditCalculatorScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable(BottomNavItem.CarPrices.route) {
                val state = viewModel.uiState.collectAsState().value
                if (state is UiState.Success) {
                    val favorites by viewModel.favorites.collectAsState()
                    CarPriceInfoScreen(
                        config = state.config,
                        favorites = favorites,
                        onFavoriteToggle = { carId -> viewModel.toggleFavorite(context, carId) },
                        onCarClick = { car ->
                            AdMobManager.showInterstitialAdWithCounter(context as Activity) {
                                onNavigateToCarDetail(car)
                            }
                        }
                    )
                }
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val title: String) {
    object CarPrices : BottomNavItem("car_prices_tab", Icons.Default.Home, "Beranda")
    object Home : BottomNavItem("home_tab", Icons.Default.ShoppingCart, "Marketplace")
    object Calculator : BottomNavItem("calculator_tab", Icons.Default.Build, "Simulasi")
}
