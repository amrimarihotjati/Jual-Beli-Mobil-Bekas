package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun GlobalShimmerLoading() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Shimmer
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier
                        .size(120.dp, 30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                    )
                    Box(modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .shimmerEffect()
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                
                // Banner Shimmer
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(24.dp))
                
                // Title Shimmer
                Box(modifier = Modifier
                    .size(150.dp, 24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            // Cards Shimmer
            items(4) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(12.dp)
                ) {
                    Box(modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(16.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerEffect()
                        )
                        Box(modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(14.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerEffect()
                        )
                        Box(modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(14.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerEffect()
                        )
                    }
                }
            }
        }
    }
}
