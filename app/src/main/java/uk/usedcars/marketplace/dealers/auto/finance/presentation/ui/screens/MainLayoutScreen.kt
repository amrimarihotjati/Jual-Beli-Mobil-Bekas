package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import uk.usedcars.marketplace.dealers.auto.finance.utils.findActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.CreditCard
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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material.icons.filled.List
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components.GlobalShimmerLoading

@Composable
fun MainLayoutScreen(
    viewModel: CarViewModel,
    onNavigateToDetail: (Marketplace) -> Unit,
    onNavigateToCarDetail: (UsedCar) -> Unit,
    onNavigateToArticleDetail: (String) -> Unit
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    
    LaunchedEffect(Unit) {
        viewModel.loadFavorites(context)
    }
    
    val items = listOf(
        BottomNavItem.CarPrices,
        BottomNavItem.Home,
        BottomNavItem.Calculator,
        BottomNavItem.Compare,
        BottomNavItem.News
    )

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .shadow(elevation = 16.dp, shape = RoundedCornerShape(32.dp), spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                    .clip(RoundedCornerShape(32.dp))
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary,
                    tonalElevation = 0.dp
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = currentRoute == item.route,
                            alwaysShowLabel = true,
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
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
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
                        AdMobManager.showInterstitialAdWithCounter(context.findActivity()) {
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
                            AdMobManager.showInterstitialAdWithCounter(context.findActivity()) {
                                onNavigateToCarDetail(car)
                            }
                        }
                    )
                } else if (state is UiState.Loading) {
                    GlobalShimmerLoading()
                }
            }
            composable(BottomNavItem.Compare.route) {
                val state = viewModel.uiState.collectAsState().value
                if (state is UiState.Success) {
                    DedicatedCompareScreen(config = state.config)
                } else if (state is UiState.Loading) {
                    GlobalShimmerLoading()
                }
            }
            composable(BottomNavItem.News.route) {
                NewsScreen(
                    onNavigateToArticleDetail = onNavigateToArticleDetail
                )
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val title: String) {
    object CarPrices : BottomNavItem("car_prices_tab", Icons.Default.Home, "Home")
    object Home : BottomNavItem("home_tab", Icons.Default.ShoppingCart, "Pasar")
    object Compare : BottomNavItem("compare_tab", Icons.Default.CompareArrows, "Adu")
    object Calculator : BottomNavItem("calculator_tab", Icons.Default.CreditCard, "Kredit")
    object News : BottomNavItem("news_tab", Icons.Default.List, "Panduan")
}
