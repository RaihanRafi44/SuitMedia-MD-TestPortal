package com.raihan.testportal.utils

import android.view.View
import com.raihan.testportal.databinding.ActivityThirdScreenBinding

class StateHandler(private val binding: ActivityThirdScreenBinding) {
    fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
        binding.rvUser.visibility = View.GONE
        binding.swipeRefreshLayout.isEnabled = false
    }

    fun showError() {
        binding.pbLoading.visibility = View.GONE
        binding.rvUser.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
    }

    fun showEmpty() {
        binding.pbLoading.visibility = View.GONE
        binding.rvUser.visibility = View.GONE
        binding.swipeRefreshLayout.isEnabled = true
        binding.swipeRefreshLayout.isRefreshing = false
        binding.tvEmpty.visibility = View.VISIBLE
    }

    fun showData() {
        binding.pbLoading.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.tvEmpty.visibility = View.GONE
        binding.rvUser.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isEnabled = true
    }
}
