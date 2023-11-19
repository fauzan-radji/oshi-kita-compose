package com.fauzan.oshikita.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.oshikita.data.Repository
import com.fauzan.oshikita.model.Member
import com.fauzan.oshikita.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Member>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<Member>> get() = _uiState

    fun getMemberById(id: Int) {
        viewModelScope.launch {
            repository.getMemberById(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { member ->
                    _uiState.value = UiState.Success(member)
                }
        }
    }
}