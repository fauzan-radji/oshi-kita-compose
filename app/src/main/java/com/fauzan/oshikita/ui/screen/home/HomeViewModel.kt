package com.fauzan.oshikita.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllMember() {
        setQuery("")
    }

    fun setOshi(id: Int, value: Boolean) {
        repository.setOshi(id, value)
    }

    fun setQuery(query: String) {
        _query.value = query
        _members.value = repository.searchMember(query)
    }
}