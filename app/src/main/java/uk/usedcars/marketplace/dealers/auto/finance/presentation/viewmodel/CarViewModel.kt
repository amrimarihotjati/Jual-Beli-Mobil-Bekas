package uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.usedcars.marketplace.dealers.auto.finance.data.repository.CarRepository
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.AppConfig
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.Marketplace
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.UsedCar

sealed class UiState {
    object Loading : UiState()
    data class Success(val config: AppConfig) : UiState()
    data class Error(val message: String) : UiState()
}

class CarViewModel(private val repository: CarRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private var clickCount = 0
    private var interstitialInterval = 0

    var selectedMarketplace: Marketplace? = null
    var selectedCar: UsedCar? = null

    init {
        fetchConfig()
    }

    fun fetchConfig() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getConfig().fold(
                onSuccess = { config ->
                    interstitialInterval = config.admobConfig.interstitialInterval
                    _uiState.value = UiState.Success(config)
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

    /**
     * Called when a marketplace card is clicked.
     * Returns true if an interstitial ad should be shown, false otherwise.
     */
    fun onMarketplaceClicked(): Boolean {
        clickCount++
        return if (interstitialInterval > 0 && clickCount % interstitialInterval == 0) {
            true
        } else {
            false
        }
    }
}
