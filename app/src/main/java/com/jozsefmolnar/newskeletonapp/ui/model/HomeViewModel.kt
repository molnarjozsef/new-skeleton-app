package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository,
) : BaseViewModel() {

    val items = repository.getCachedNews().asStateFlow()

    init {
        fetchLatestNews()
    }

    fun fetchLatestNews() {
        viewModelScope.launch {
            trackProgress {
                repository.fetchLatestNews()
            }
        }
    }
}
