package com.raihan.testportal.presentation.thirdscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raihan.testportal.data.repository.UserPagingRepository

class ThirdScreenViewModelFactory(private val repository: UserPagingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
