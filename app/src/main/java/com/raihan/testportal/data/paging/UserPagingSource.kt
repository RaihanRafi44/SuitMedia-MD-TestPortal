package com.raihan.testportal.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raihan.testportal.data.mapper.toUserList
import com.raihan.testportal.data.model.User
import com.raihan.testportal.data.source.network.service.TestPortalApiService

class UserPagingSource(private val service: TestPortalApiService) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        return try {
            val response = service.getUsers(page = page, perPage = params.loadSize)
            val userList = response.data.toUserList().sortedBy { it.id }

            LoadResult.Page(
                data = userList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (userList.isEmpty()) null else page + 1,
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
