package com.fauzan.oshikita.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fauzan.oshikita.data.Repository
import com.fauzan.oshikita.ui.screen.detail.DetailViewModel
import com.fauzan.oshikita.ui.screen.favorite.FavoriteViewModel
import com.fauzan.oshikita.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository)
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(repository)

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }
}