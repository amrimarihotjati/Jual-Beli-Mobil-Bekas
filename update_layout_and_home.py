import re

# 1. Update MainLayoutScreen.kt
with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/MainLayoutScreen.kt', 'r') as f:
    content = f.read()

# Replace imports to include MonetizationOn and ShoppingCart
if "MonetizationOn" not in content:
    content = content.replace("import androidx.compose.material.icons.filled.Home", 
                              "import androidx.compose.material.icons.filled.Home\nimport androidx.compose.material.icons.filled.ShoppingCart\nimport androidx.compose.material.icons.filled.MonetizationOn")

# Update BottomNavItem sealed class
new_sealed_class = """sealed class BottomNavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val title: String) {
    object CarPrices : BottomNavItem("car_prices_tab", Icons.Default.Home, "Beranda")
    object Home : BottomNavItem("home_tab", Icons.Default.ShoppingCart, "Marketplace")
    object Calculator : BottomNavItem("calculator_tab", Icons.Default.MonetizationOn, "Simulasi")
}"""
content = re.sub(r'sealed class BottomNavItem.*?\}', new_sealed_class, content, flags=re.DOTALL)

# Update items list order
content = content.replace("val items = listOf(\n        BottomNavItem.Home,\n        BottomNavItem.Calculator,\n        BottomNavItem.CarPrices\n    )",
                          "val items = listOf(\n        BottomNavItem.CarPrices,\n        BottomNavItem.Home,\n        BottomNavItem.Calculator\n    )")

# Update NavHost startDestination
content = content.replace("startDestination = BottomNavItem.Home.route", "startDestination = BottomNavItem.CarPrices.route")

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/MainLayoutScreen.kt', 'w') as f:
    f.write(content)

# 2. Update MainScreen.kt to remove FAB and Scaffold
with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/MainScreen.kt', 'r') as f:
    content_main = f.read()

# The signature of MainScreen needs to drop the FAB 
new_main_screen = """@Composable
fun MainScreen(
    viewModel: CarViewModel,
    onNavigateToDetail: (Marketplace) -> Unit,
    onNavigateToCalculator: () -> Unit = {}
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
            LaunchedEffect(Unit) {
                AdMobManager.loadInterstitialAd(context, state.config.admobConfig.interstitialId)
            }
            
            // Removed Scaffold & FAB
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
"""

content_main = re.sub(r'@Composable\s*fun MainScreen\(.*?\n}\s*\n', new_main_screen, content_main, flags=re.DOTALL)

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/MainScreen.kt', 'w') as f:
    f.write(content_main)

