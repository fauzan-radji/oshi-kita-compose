package com.fauzan.oshikita.di

import com.fauzan.oshikita.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}