package com.raihan.testportal.presentation.thirdscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.raihan.testportal.R
import com.raihan.testportal.data.repository.UserPagingRepositoryImpl
import com.raihan.testportal.data.source.network.service.TestPortalApiService
import com.raihan.testportal.databinding.ActivityThirdScreenBinding
import com.raihan.testportal.presentation.thirdscreen.adapter.UserAdapter
import com.raihan.testportal.utils.StateHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding

    private val viewModel: ThirdScreenViewModel by viewModels {
        ThirdScreenViewModelFactory(UserPagingRepositoryImpl(TestPortalApiService()))
    }

    private val thirdAdapter: UserAdapter by lazy {
        UserAdapter { user ->
            val resultIntent =
                Intent().apply {
                    putExtra("selected_user", user)
                }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private lateinit var stateHandler: StateHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        stateHandler = StateHandler(binding)

        binding.btnBack.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
        setupUser()
        getUserAll()
        binding.swipeRefreshLayout.setOnRefreshListener {
            thirdAdapter.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupUser() {
        binding.rvUser.adapter = thirdAdapter
    }

    private fun getUserAll() {
        stateHandler.showLoading()

        lifecycleScope.launch {
            viewModel.userAll().collectLatest { pagingData ->
                thirdAdapter.submitData(pagingData)
            }
        }

        thirdAdapter.addLoadStateListener { loadState ->

            if (loadState.source.refresh is LoadState.Loading) {
                stateHandler.showLoading()
            } else {
                stateHandler.showData()

                val isEmpty = thirdAdapter.itemCount == 0 && loadState.source.refresh is LoadState.NotLoading
                if (isEmpty) {
                    stateHandler.showEmpty()
                }
            }

            if (loadState.source.refresh is LoadState.Error) {
                stateHandler.showError()
            }
        }
    }
}
