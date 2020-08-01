package com.axonactive.sharing.paging.feature.user.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.axonactive.sharing.paging.databinding.ActivityUserListBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding

    private val viewModel by viewModels<UserViewModel>()
    private val userAdapter = UserAdapter(UserComparator)

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUser.adapter = userAdapter.withLoadStateFooter(UserLoadStateAdapter {
            userAdapter.retry()
        })
        binding.wrl.setOnRefreshListener {
            userAdapter.refresh()
        }
        userAdapter.addLoadStateListener { loadState ->
            binding.wrl.isRefreshing = loadState.refresh is LoadState.Loading
        }
        lifecycleScope.launch {
            viewModel.users.collectLatest { data ->
                userAdapter.submitData(data)
            }
        }
    }
}