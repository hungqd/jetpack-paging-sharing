package com.axonactive.sharing.paging.feature.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.axonactive.sharing.paging.R
import com.axonactive.sharing.paging.data.model.User
import com.axonactive.sharing.paging.databinding.LayoutUserItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class UserAdapter(diffCallback: DiffUtil.ItemCallback<User>) :
    PagingDataAdapter<User, UserAdapter.UserViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = LayoutUserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    class UserViewHolder(
        private val binding: LayoutUserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvName.text = "${user.name.first} ${user.name.last}"
            binding.tvEmail.text = user.email
            Glide.with(itemView)
                .load(user.picture["medium"])
                .transform(RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.avatar_corner_radius)))
                .into(binding.ivAvatar)
        }
    }
}