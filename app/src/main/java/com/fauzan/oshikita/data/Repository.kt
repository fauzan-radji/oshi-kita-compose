package com.fauzan.oshikita.data

import com.fauzan.oshikita.model.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {

    private val members = mutableListOf<Member>()

    init {
        if (members.isEmpty()) {
            FakeDataSource.dummyMembers.forEach {
                members.add(it)
            }
        }
    }

    fun getAllMember(): Flow<List<Member>> {
        return flowOf(members)
    }

    fun getMemberById(id: Int): Flow<Member> {
        return flowOf(members.first { it.id == id })
    }

    fun setOshi(id: Int, value: Boolean): Flow<Boolean> {
        val index = members.indexOfFirst { it.id == id }
        if (index >= 0) {
            val member = members[index]
            members[index] = member.copy(isOshi = value)

            return flowOf(true)
        }

        return flowOf(false)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}