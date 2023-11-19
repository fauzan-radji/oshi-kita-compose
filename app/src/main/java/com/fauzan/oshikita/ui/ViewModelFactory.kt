package com.fauzan.oshikita.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fauzan.oshikita.data.Repository
import com.fauzan.oshikita.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(repository) as T
            }
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}