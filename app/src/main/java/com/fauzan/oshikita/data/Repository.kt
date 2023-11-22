package com.fauzan.oshikita.data

import com.fauzan.oshikita.model.Member

class Repository {

    private val members = mutableListOf<Member>()

    init {
        if (members.isEmpty()) {
            FakeDataSource.dummyMembers.forEach {
                members.add(it)
            }
        }
    }

    fun getAllMember(): List<Member> = members
    fun getMemberById(id: Int): Member = members.first { it.id == id }
    fun getOshi(): List<Member> = members.filter { it.isOshi }

    fun setOshi(id: Int, value: Boolean) {
        val index = members.indexOfFirst { it.id == id }
        members[index] = members[index].copy(isOshi = value)
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