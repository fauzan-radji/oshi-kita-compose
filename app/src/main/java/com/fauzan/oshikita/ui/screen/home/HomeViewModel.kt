package com.fauzan.oshikita.ui.screen.home

import androidx.lifecycle.ViewModel
import com.fauzan.oshikita.data.Repository
import com.fauzan.oshikita.model.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _members: MutableStateFlow<List<Member>> = MutableStateFlow(
        repository.getAllMember()
    )
    val members = _members.asStateFlow()

    fun getAllMember() {
        _members.value = repository.getAllMember()
    }

    fun setOshi(id: Int, value: Boolean) {
        repository.setOshi(id, value)
    }
}