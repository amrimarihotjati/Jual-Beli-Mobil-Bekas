package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import uk.usedcars.marketplace.dealers.auto.finance.utils.findActivity
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components.ShimmerAsyncImage
import android.app.Activity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.UsedCar
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components.NativeAdViewComposable
import uk.usedcars.marketplace.dealers.auto.finance.utils.AdMobManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DedicatedCompareScreen(
    config: AppConfig
) {
    val context = LocalContext.current
    var car1 by remember { mutableStateOf<UsedCar?>(null) }
    var car2 by remember { mutableStateOf<UsedCar?>(null) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheetFor by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Komparasi Pintar", fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Car 1 Column
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    val c1 = car1
                    if (c1 == null) {
                        AddCarButton { showSheetFor = 1 }
                    } else {
                        CarHeader(c1) { car1 = null }
                    }
                }
                
                // Car 2 Column
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    val c2 = car2
                    if (c2 == null) {
                        AddCarButton { showSheetFor = 2 }
                    } else {
                        CarHeader(c2) { car2 = null }
                    }
                }
            }

            // Giant Native Ad right between headers and specs
            Spacer(modifier = Modifier.height(8.dp))
            NativeAdViewComposable(cacheKey = "dedicated_compare_ad")
            Spacer(modifier = Modifier.height(16.dp))

            val c1 = car1
            val c2 = car2
            if (c1 != null && c2 != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        SmartCompareRow("Harga (Juta)", c1.priceRange, c2.priceRange, type = CompareType.LOWER_IS_BETTER)
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 12.dp))
                        SmartCompareRow("Tahun", c1.year, c2.year, type = CompareType.HIGHER_IS_BETTER)
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 12.dp))
                        SmartCompareRow("Kilometer", c1.mileage, c2.mileage, type = CompareType.LOWER_IS_BETTER)
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 12.dp))
                        SmartCompareRow("Kapasitas Penumpang", c1.seats, c2.seats, type = CompareType.NEUTRAL)
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 12.dp))
                        SmartCompareRow("Transmisi", c1.transmission, c2.transmission, type = CompareType.NEUTRAL)
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 12.dp))
                        SmartCompareRow("Bahan Bakar", c1.fuelType, c2.fuelType, type = CompareType.NEUTRAL)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                    Text("Pilih dua mobil untuk melihat hasil komparasi", color = Color.Gray, textAlign = TextAlign.Center)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    if (showSheetFor != null) {
        ModalBottomSheet(
            onDismissRequest = { showSheetFor = null },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text("Pilih Mobil", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.foundation.lazy.LazyColumn {
                    items(config.usedCars.size) { index ->
                        val compareCar = config.usedCars[index]
                        // Prevent selecting the same car twice
                        if ((showSheetFor == 1 && compareCar.id != car2?.id) || (showSheetFor == 2 && compareCar.id != car1?.id)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        AdMobManager.showInterstitialAdWithCounter(context.findActivity()) {
                                            if (showSheetFor == 1) car1 = compareCar else car2 = compareCar
                                            showSheetFor = null
                                        }
                                    }
                                    .padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ShimmerAsyncImage(
                                    model = compareCar.imageUrls.firstOrNull() ?: "",
                                    contentDescription = compareCar.name,
                                    modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(compareCar.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                                    Text(compareCar.priceRange, color = MaterialTheme.colorScheme.primary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun AddCarButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.3f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add Car", modifier = Modifier.size(40.dp), tint = Color.Gray)
    }
}

@Composable
fun CarHeader(car: UsedCar, onRemove: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable(onClick = onRemove)) {
        ShimmerAsyncImage(
            model = car.imageUrls.firstOrNull() ?: "",
            contentDescription = car.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(car.brand, fontSize = 12.sp, color = Color.Gray)
        Text(car.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 2, textAlign = TextAlign.Center)
    }
}

enum class CompareType { HIGHER_IS_BETTER, LOWER_IS_BETTER, NEUTRAL }

@Composable
fun SmartCompareRow(label: String, val1: String, val2: String, type: CompareType) {
    val num1 = val1.replace(Regex("[^0-9]"), "").toDoubleOrNull() ?: 0.0
    val num2 = val2.replace(Regex("[^0-9]"), "").toDoubleOrNull() ?: 0.0

    var win1 = false
    var win2 = false

    if (type == CompareType.HIGHER_IS_BETTER) {
        if (num1 > num2) win1 = true
        if (num2 > num1) win2 = true
    } else if (type == CompareType.LOWER_IS_BETTER) {
        if (num1 < num2) win1 = true
        if (num2 < num1) win2 = true
    }

    Column {
        Text(text = label, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            CompareCell(val1, win1, modifier = Modifier.weight(1f))
            CompareCell(val2, win2, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CompareCell(text: String, isWinner: Boolean, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically, 
        modifier = modifier
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
            .background(if (isWinner) Color(0xFFE8F5E9) else Color.Transparent)
            .padding(horizontal = if (isWinner) 8.dp else 0.dp, vertical = if (isWinner) 4.dp else 0.dp)
    ) {
        Text(
            text = text,
            fontSize = if (isWinner) 16.sp else 14.sp,
            fontWeight = if (isWinner) FontWeight.ExtraBold else FontWeight.SemiBold,
            color = if (isWinner) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurface
        )
        if (isWinner) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Default.CheckCircle, contentDescription = "Winner", tint = Color(0xFF2E7D32), modifier = Modifier.size(16.dp))
        }
    }
}
