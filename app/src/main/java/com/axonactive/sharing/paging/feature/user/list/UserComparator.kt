package com.axonactive.sharing.paging.feature.user.list

import androidx.recyclerview.widget.DiffUtil
import com.axonactive.sharing.paging.data.model.User

object UserComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}