import re

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/MainScreen.kt', 'r') as f:
    content = f.read()

# Update MainScreen signature and add Scaffold
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
            
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onNavigateToCalculator,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ) {
                        Icon(androidx.compose.material.icons.Icons.Default.Star, contentDescription = "Simulasi Kredit")
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
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
    }
}
"""

content = re.sub(r'@Composable\s*fun MainScreen\(.*?\n}\s*\n', new_main_screen, content, flags=re.DOTALL)


# Update MainContent for search and filter
new_main_content = """@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainContent(config: AppConfig, onMarketplaceClick: (Marketplace) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredMarketplaces = config.marketplaces.filter { 
        it.name.contains(searchQuery, ignoreCase = true) || 
        it.tags.any { tag -> tag.contains(searchQuery, ignoreCase = true) } 
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp), // Extra padding for FAB
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column {
                Text(
                    text = "Hai, Selamat Datang!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari mobil atau fitur (Bisa Cicil, Garansi...)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )

                // Top Section: Slideshow
                val pagerState = rememberPagerState(pageCount = { config.slideshow.size })
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) { page ->
                    val item = config.slideshow[page]
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                    ) {
                        AsyncImage(
                            model = item.imageUrl,
                            contentDescription = item.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.3f))
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

                Text(
                    text = "Marketplace Rekomendasi",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        items(filteredMarketplaces) { marketplace ->"""

content = re.sub(r'@OptIn\(ExperimentalFoundationApi::class\)\s*@Composable\s*fun MainContent\(config: AppConfig, onMarketplaceClick: \(Marketplace\) -> Unit\) \{.*?items\(config\.marketplaces\) \{ marketplace ->', new_main_content, content, flags=re.DOTALL)

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/MainScreen.kt', 'w') as f:
    f.write(content)
