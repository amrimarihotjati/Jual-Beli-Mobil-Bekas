package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components.ShimmerAsyncImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(onFinishIntro: () -> Unit) {
    val introPages = listOf(
        IntroPage("Selamat Datang", "Platform jual beli mobil bekas terpercaya. Temukan mobil impianmu dengan mudah.", "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images/c2_0.jpg"),
        IntroPage("Banyak Pilihan", "Temukan berbagai macam mobil dari berbagai marketplace terkemuka di satu tempat.", "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images/c3_0.jpg"),
        IntroPage("Mudah & Cepat", "Transaksi aman, cepat, dan transparan. Wujudkan mobil impianmu sekarang juga.", "https://raw.githubusercontent.com/amrimarihotjati/my-apps-api/main/images/c12_0.jpg")
    )

    val pagerState = rememberPagerState(pageCount = { introPages.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            if (pagerState.currentPage != introPages.lastIndex) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onFinishIntro) {
                        Text("Lewati", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(introPages.size) { iteration ->
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                        val width = if (pagerState.currentPage == iteration) 24.dp else 10.dp
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(CircleShape)
                                .background(color)
                                .height(10.dp)
                                .width(width)
                        )
                    }
                }

                if (pagerState.currentPage == introPages.lastIndex) {
                    Button(
                        onClick = onFinishIntro,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Mulai Sekarang", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Selanjutnya", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Car Image
                Box(
                    modifier = Modifier
                        .size(240.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    ShimmerAsyncImage(
                        model = introPages[page].imageUrl,
                        contentDescription = "Intro Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                
                Spacer(modifier = Modifier.height(48.dp))
                
                Text(
                    text = introPages[page].title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = introPages[page].description,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

data class IntroPage(val title: String, val description: String, val imageUrl: String)
