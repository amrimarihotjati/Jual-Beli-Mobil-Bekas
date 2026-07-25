package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

            // Side by side comparison
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Column 1
                Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                    car1?.let { CarComparisonColumn(it) }
                }

                // Divider
                VerticalDivider()

                // Column 2
                Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                    car2?.let { CarComparisonColumn(it) }
                }
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

@Composable
fun CarComparisonColumn(car: UsedCar) {
    val imgUrl = car.imageUrl ?: car.getGalleryImages().firstOrNull() ?: ""
    ShimmerAsyncImage(
        model = imgUrl,
        contentDescription = car.model,
        modifier = Modifier.fillMaxWidth().height(100.dp),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(car.brand, style = MaterialTheme.typography.labelSmall, color = androidx.compose.ui.graphics.Color.Gray)
    Text(car.model.ifEmpty { car.name ?: "" }, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    
    Spacer(modifier = Modifier.height(16.dp))
    
    val priceList = car.getPriceHistoryList()
    if (priceList.isEmpty()) {
        Text("Data harga tidak tersedia", fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.Gray)
    } else {
        priceList.forEach { item ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Tahun ${item.year}", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${item.transmission} • ${item.fuel}", fontSize = 10.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(item.avgPrice, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}
