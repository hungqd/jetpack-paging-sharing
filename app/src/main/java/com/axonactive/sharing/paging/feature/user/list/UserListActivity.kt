package com.axonactive.sharing.paging.feature.user.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import com.axonactive.sharing.paging.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding

    private val viewModel by viewModels<UserViewModel>()
    private val userAdapter = UserAdapter(UserComparator)

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
        viewModel.users.observe(this, Observer { users ->
            userAdapter.submitData(lifecycle, users)
        })
    }
}