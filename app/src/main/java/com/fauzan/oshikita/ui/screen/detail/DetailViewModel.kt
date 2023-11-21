package com.fauzan.oshikita.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.oshikita.data.Repository
import com.fauzan.oshikita.model.Member
import com.fauzan.oshikita.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Member>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Member>> get() = _uiState

    private val _oshiState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun oshiState(id: Int): StateFlow<Boolean> {
        viewModelScope.launch {
            repository.getMemberById(id)
                .catch {
                    _oshiState.value = false
                }
                .collect { member ->
                    _oshiState.value = member.isOshi
                }
        }
        return _oshiState
    }

    fun getMemberById(id: Int) {
        viewModelScope.launch {
            repository.getMemberById(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { member ->
                    _uiState.value = UiState.Success(member)
                    _oshiState.value = member.isOshi
                }
        }
    }

    fun setOshi(id: Int, value: Boolean) {
        viewModelScope.launch {
            repository.setOshi(id, value)
                .catch {
                    _oshiState.value = false
                }
                .collect {
                    _oshiState.value = it
                }
        }
    }
}