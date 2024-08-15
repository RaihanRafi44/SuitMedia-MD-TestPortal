package com.raihan.testportal.presentation.thirdscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.raihan.testportal.data.model.User
import com.raihan.testportal.data.repository.UserPagingRepository
import kotlinx.coroutines.flow.Flow

class ThirdScreenViewModel(private val repository: UserPagingRepository) : ViewModel() {
    fun userAll(): Flow<PagingData<User>> = repository.getUsers().cachedIn(viewModelScope)
}
