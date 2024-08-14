package com.raihan.testportal.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raihan.testportal.data.model.User
import com.raihan.testportal.data.paging.UserPagingSource
import com.raihan.testportal.data.source.network.service.TestPortalApiService
import kotlinx.coroutines.flow.Flow

interface UserPagingRepository {
    fun getUsers(): Flow<PagingData<User>>
}

class UserPagingRepositoryImpl(private val apiService: TestPortalApiService) : UserPagingRepository {
    override fun getUsers(): Flow<PagingData<User>> =
        Pager(
            config =
                PagingConfig(
                    pageSize = 12,
                    enablePlaceholders = false,
                ),
            pagingSourceFactory = { UserPagingSource(apiService) },
        ).flow
}
