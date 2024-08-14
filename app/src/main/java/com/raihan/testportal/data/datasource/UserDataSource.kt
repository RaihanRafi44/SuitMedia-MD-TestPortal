package com.raihan.testportal.data.datasource

import com.raihan.testportal.data.source.network.model.UserResponse
import com.raihan.testportal.data.source.network.service.TestPortalApiService

interface UserDataSource {
    suspend fun getUserData(
        pageUser: Int,
        perPageUser: Int,
    ): UserResponse
}

class UserApiDataSource(private val service: TestPortalApiService) : UserDataSource {
    override suspend fun getUserData(
        pageUser: Int,
        perPageUser: Int,
    ): UserResponse {
        return service.getUsers(
            page = pageUser,
            perPage = perPageUser,
        )
    }
}
