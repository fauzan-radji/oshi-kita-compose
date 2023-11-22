package com.fauzan.oshikita.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.fauzan.oshikita.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteViewModel(private val repository: Repository) : ViewModel() {
    private val _oshi = MutableStateFlow(
        repository.getOshi()
    )
    val oshi = _oshi.asStateFlow()

    fun getOshi() {
        _oshi.value = repository.getOshi()
    }

    fun setOshi(id: Int, value: Boolean) {
        repository.setOshi(id, value)
        _oshi.value = repository.getOshi()
    }
}