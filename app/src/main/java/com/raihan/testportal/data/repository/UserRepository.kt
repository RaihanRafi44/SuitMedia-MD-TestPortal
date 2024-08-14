package com.raihan.testportal.data.repository

import com.raihan.testportal.data.datasource.UserDataSource
import com.raihan.testportal.data.mapper.toUserList
import com.raihan.testportal.data.model.User
import com.raihan.tryinternsuit.utils.ResultWrapper
import com.raihan.tryinternsuit.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(
        pageUser: Int,
        perPageUser: Int,
    ): Flow<ResultWrapper<List<User>>>
}

class UserRepositoryImpl(private val dataSource: UserDataSource) : UserRepository {
    override fun getUsers(
        pageUser: Int,
        perPageUser: Int,
    ): Flow<ResultWrapper<List<User>>> {
        return proceedFlow {
            dataSource.getUserData(pageUser, perPageUser).data.toUserList()
        }
    }
}
