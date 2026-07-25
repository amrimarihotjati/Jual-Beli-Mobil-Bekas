package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.UsedCar
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.PriceHistoryItem
import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components.ShimmerAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen(
    config: AppConfig,
    onBack: () -> Unit
) {
    val allCars = config.usedCars
    var car1 by remember { mutableStateOf<UsedCar?>(allCars.getOrNull(0)) }
    var car2 by remember { mutableStateOf<UsedCar?>(allCars.getOrNull(1)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bandingkan Harga") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Dropdowns
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    CarDropdown(
                        selectedCar = car1,
                        cars = allCars,
                        onCarSelected = { car1 = it }
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    CarDropdown(
                        selectedCar = car2,
                        cars = allCars,
                        onCarSelected = { car2 = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            // Image and Name Row
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    car1?.let { CarHeader(it) }
                }
                Box(modifier = Modifier.weight(1f)) {
                    car2?.let { CarHeader(it) }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            if (car1 != null && car2 != null) {
                Text("Komparasi Harga Rata-rata per Tahun", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                
                val years1 = car1!!.getPriceHistoryList().map { it.year }
                val years2 = car2!!.getPriceHistoryList().map { it.year }
                val allYears = (years1 + years2).distinct().sortedDescending()
                
                if (allYears.isEmpty()) {
                    Text("Data harga tidak tersedia", color = androidx.compose.ui.graphics.Color.Gray)
                } else {
                    allYears.forEach { year ->
                        val price1 = car1!!.getPriceHistoryList().filter { it.year == year }
                        val price2 = car2!!.getPriceHistoryList().filter { it.year == year }
                        
                        YearCompareRow(year = year, list1 = price1, list2 = price2)
                    }
                }
            }
        }
    }
}

@Composable
fun CarHeader(car: UsedCar) {
    Column {
        val imgUrl = car.imageUrl ?: car.getGalleryImages().firstOrNull() ?: ""
        ShimmerAsyncImage(
            model = imgUrl,
            contentDescription = car.model,
            modifier = Modifier.fillMaxWidth().height(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(car.brand, style = MaterialTheme.typography.labelSmall, color = androidx.compose.ui.graphics.Color.Gray)
        Text(car.model.ifEmpty { car.name ?: "" }, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 2)
    }
}

@Composable
fun YearCompareRow(year: Int, list1: List<PriceHistoryItem>, list2: List<PriceHistoryItem>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Text("Tahun $year", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            // To make it simple, we compare the lowest price for each year if there are multiple (e.g. manual vs auto)
            val minPrice1Item = list1.minByOrNull { parsePrice(it.avgPrice) }
            val minPrice2Item = list2.minByOrNull { parsePrice(it.avgPrice) }
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    if (minPrice1Item != null) {
                        PriceItemView(
                            item = minPrice1Item, 
                            isWinner = isWinner(minPrice1Item, minPrice2Item)
                        )
                    } else {
                        Text("-", color = androidx.compose.ui.graphics.Color.Gray)
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (minPrice2Item != null) {
                        PriceItemView(
                            item = minPrice2Item, 
                            isWinner = isWinner(minPrice2Item, minPrice1Item)
                        )
                    } else {
                        Text("-", color = androidx.compose.ui.graphics.Color.Gray)
                    }
                }
            }
        }
    }
}

fun parsePrice(priceStr: String): Long {
    // Expected format "Rp 150 Juta" or "150.000.000"
    val cleaned = priceStr.lowercase().replace(Regex("[^0-9]"), "")
    val value = cleaned.toLongOrNull() ?: 0L
    // if the string has "juta", multiply by 1_000_000 if the number is less than 10000
    if (priceStr.lowercase().contains("juta") && value < 10000) {
        return value * 1_000_000L
    }
    return value
}

fun isWinner(item: PriceHistoryItem, otherItem: PriceHistoryItem?): Boolean {
    if (otherItem == null) return true // default winner if other has no price
    val myPrice = parsePrice(item.avgPrice)
    val otherPrice = parsePrice(otherItem.avgPrice)
    return myPrice < otherPrice && myPrice > 0
}

@Composable
fun PriceItemView(item: PriceHistoryItem, isWinner: Boolean) {
    Column {
        Text("${item.transmission} • ${item.fuel}", fontSize = 10.sp, color = androidx.compose.ui.graphics.Color.Gray)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(item.avgPrice, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            if (isWinner) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.CheckCircle, contentDescription = "Termurah", tint = androidx.compose.ui.graphics.Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDropdown(
    selectedCar: UsedCar?,
    cars: List<UsedCar>,
    onCarSelected: (UsedCar) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedCar?.model?.ifEmpty { selectedCar.name ?: "" } ?: "Pilih Mobil",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            // Note: MenuAnchorType is implicitly handled in newer versions or menuAnchor() without args is deprecated. 
            // We use modifier = Modifier.menuAnchor()
            modifier = Modifier.menuAnchor(),
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 12.sp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            cars.forEach { car ->
                DropdownMenuItem(
                    text = { Text(car.model.ifEmpty { car.name ?: "" }, fontSize = 12.sp) },
                    onClick = {
                        onCarSelected(car)
                        expanded = false
                    }
                )
            }
        }
    }
}
