package uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.usedcars.marketplace.dealers.auto.finance.utils.AdMobManager
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCalculatorScreen(onBack: () -> Unit) {
    var carPrice by remember { mutableStateOf("") }
    var dpPercent by remember { mutableStateOf("20") }
    var tenor by remember { mutableStateOf("5") }
    var interestRate by remember { mutableStateOf("8.5") }
    
    var cicilan by remember { mutableStateOf(0.0) }
    var dpNominal by remember { mutableStateOf(0.0) }
    var pokokHutang by remember { mutableStateOf(0.0) }
    var showResult by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val activity = context as? Activity

    fun calculate() {
        val price = carPrice.toDoubleOrNull() ?: 0.0
        val dpP = dpPercent.toDoubleOrNull() ?: 0.0
        val t = tenor.toIntOrNull() ?: 0
        val rate = interestRate.toDoubleOrNull() ?: 0.0

        if (price > 0 && t > 0) {
            dpNominal = price * (dpP / 100)
            pokokHutang = price - dpNominal
            val bungaTotal = pokokHutang * (rate / 100) * t
            val totalHutang = pokokHutang + bungaTotal
            cicilan = totalHutang / (t * 12)
            showResult = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simulasi Kredit", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = carPrice,
                        onValueChange = { carPrice = it },
                        label = { Text("Harga Mobil (Rp)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = dpPercent,
                        onValueChange = { dpPercent = it },
                        label = { Text("Down Payment / DP (%)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = tenor,
                        onValueChange = { tenor = it },
                        label = { Text("Tenor (Tahun)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = interestRate,
                        onValueChange = { interestRate = it },
                        label = { Text("Bunga per Tahun (%)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            if (activity != null) {
                                // AdMobManager will show ad, then execute calculate
                                AdMobManager.showInterstitialAd(activity) {
                                    calculate()
                                    // Preload next ad
                                    AdMobManager.loadInterstitialAd(context, "ca-app-pub-3940256099942544~3347511713")
                                }
                            } else {
                                calculate()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Hitung Simulasi", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            if (showResult) {
                val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                formatter.maximumFractionDigits = 0

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Info, contentDescription = "Info", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Estimasi Hasil Kalkulasi", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        ResultRow("DP Nominal", formatter.format(dpNominal))
                        ResultRow("Pokok Hutang", formatter.format(pokokHutang))
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Cicilan per Bulan", color = Color.Gray, fontSize = 14.sp)
                        Text(formatter.format(cicilan), fontWeight = FontWeight.Bold, fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
                    }
                }
                
                // Bottom Banner Ad Placeholder Space
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(16.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Ad Banner Placement", color = Color.DarkGray)
                }
            }
        }
    }
}

@Composable
fun ResultRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text(value, fontWeight = FontWeight.SemiBold)
    }
}
