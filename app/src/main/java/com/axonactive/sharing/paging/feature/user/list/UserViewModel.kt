package com.axonactive.sharing.paging.feature.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.axonactive.sharing.paging.data.repository.UserPagingSource
import com.axonactive.sharing.paging.data.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repo = UserRepository()

    val users = Pager(PagingConfig(PAGE_SIZE)) { UserPagingSource(repo) }
        .flow
        .cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 10
    }
}