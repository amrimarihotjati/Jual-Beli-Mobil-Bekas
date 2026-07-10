package uk.usedcars.marketplace.dealers.auto.finance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.usedcars.marketplace.dealers.auto.finance.data.repository.CarRepository
import uk.usedcars.marketplace.dealers.auto.finance.domain.model.NewsItem

class NewsViewModel(private val repository: CarRepository) : ViewModel() {
    private val _news = MutableStateFlow<List<NewsItem>>(emptyList())
    val news: StateFlow<List<NewsItem>> = _news.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.getNews().fold(
                onSuccess = { response ->
                    if (response.status == "ok" && response.items != null) {
                        _news.value = response.items
                    } else {
                        _error.value = "Gagal memuat berita."
                    }
                },
                onFailure = { e ->
                    _error.value = e.message ?: "Terjadi kesalahan."
                }
            )
            _isLoading.value = false
        }
    }
}
