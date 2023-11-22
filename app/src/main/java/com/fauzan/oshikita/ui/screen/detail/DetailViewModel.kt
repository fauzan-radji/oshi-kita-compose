package com.fauzan.oshikita.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.fauzan.oshikita.data.Repository
import com.fauzan.oshikita.model.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _member: MutableStateFlow<Member?> = MutableStateFlow(null)
    val member = _member.asStateFlow()

    fun getMemberById(id: Int) {
        _member.value = repository.getMemberById(id)
    }

    fun setOshi(id: Int, value: Boolean) {
        repository.setOshi(id, value)
        _member.value = _member.value?.copy(isOshi = value)
    }
}