package com.fauzan.oshikita.ui.screen.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fauzan.oshikita.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteViewModel(private val repository: Repository) : ViewModel() {
    private val _oshi = MutableStateFlow(
        repository.getOshi()
    )
    val oshi = _oshi.asStateFlow()

    val _query: MutableState<String> = mutableStateOf("")
    val query: State<String> get() = _query

    fun getOshi() {
        setQuery("")
    }

    fun setOshi(id: Int, value: Boolean) {
        repository.setOshi(id, value)
        _oshi.value = repository.getOshi()
    }

    fun setQuery(query: String) {
        _query.value = query
        _oshi.value = repository.searchOshi(query)
    }
}