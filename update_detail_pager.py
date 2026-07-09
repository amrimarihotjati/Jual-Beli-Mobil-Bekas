import re

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/CarDetailScreen.kt', 'r') as f:
    content = f.read()

if "import androidx.compose.foundation.pager.HorizontalPager" not in content:
    content = content.replace("import androidx.compose.foundation.background",
                              "import androidx.compose.foundation.background\nimport androidx.compose.foundation.pager.HorizontalPager\nimport androidx.compose.foundation.pager.rememberPagerState\nimport androidx.compose.foundation.shape.CircleShape\nimport androidx.compose.ui.draw.clip")

old_image_block = """            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color.White)
            ) {
                AsyncImage(
                    model = car.imageUrl,
                    contentDescription = car.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }"""

new_pager_block = """            val pagerState = rememberPagerState(pageCount = { if (car.imageUrls.isEmpty()) 1 else car.imageUrls.size })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color.White)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    AsyncImage(
                        model = car.imageUrls.getOrNull(page) ?: "",
                        contentDescription = car.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                
                // Indicators
                if (car.imageUrls.size > 1) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(car.imageUrls.size) { iteration ->
                            val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(8.dp)
                            )
                        }
                    }
                }
            }"""

content = content.replace(old_image_block, new_pager_block)

# Just in case `car.imageUrl` was not correctly replaced, let's also try generic regex
content = re.sub(
    r'Box\(\s*modifier = Modifier\s*\.fillMaxWidth\(\)\s*\.height\(250\.dp\)\s*\.background\(Color\.White\)\s*\)\s*\{\s*AsyncImage\(\s*model = car\.imageUrl[^\}]*\}\s*\}',
    new_pager_block,
    content
)

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens/CarDetailScreen.kt', 'w') as f:
    f.write(content)

