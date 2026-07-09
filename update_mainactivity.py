import re

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/MainActivity.kt', 'r') as f:
    content = f.read()

# Add import for MainLayoutScreen
if "MainLayoutScreen" not in content:
    content = content.replace(
        "import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.MainScreen",
        "import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.MainScreen\nimport uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.MainLayoutScreen\nimport uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens.CarDetailScreen"
    )

new_nav_host = """NavHost(navController = navController, startDestination = "splash") {
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
                            viewModel.selectedCar?.let { car ->
                                if (state is uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel.UiState.Success) {
                                    CarDetailScreen(
                                        car = car,
                                        config = state.config,
                                        onBack = { navController.popBackStack() }
                                    )
                                }
                            }
                        }
                    }"""

content = re.sub(r'NavHost\(navController = navController, startDestination = "splash"\) \{.*?\}\s*\}\s*\}\s*\}\s*\}\s*\}$', new_nav_host + "\n                }\n            }\n        }\n    }\n}", content, flags=re.DOTALL)

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/MainActivity.kt', 'w') as f:
    f.write(content)
