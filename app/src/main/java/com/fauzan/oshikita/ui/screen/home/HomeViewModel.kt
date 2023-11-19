package com.fauzan.oshikita.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.oshikita.data.Repository
import com.fauzan.oshikita.model.Member
import com.fauzan.oshikita.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Member>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Member>>> get() = _uiState

    fun getAllMember() {
        viewModelScope.launch {
            repository.getAllMember()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { oshis ->
                    _uiState.value = UiState.Success(oshis)
                }
        }
    }
}