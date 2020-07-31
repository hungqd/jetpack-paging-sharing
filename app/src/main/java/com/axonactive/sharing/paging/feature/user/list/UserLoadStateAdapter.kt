package com.axonactive.sharing.paging.feature.user.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axonactive.sharing.paging.R

class UserLoadStateAdapter(
    private val retryCallback: () -> Unit
) : LoadStateAdapter<UserLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_load_state_item, parent, false)
        layout.findViewById<TextView>(R.id.tv_error).setOnClickListener {
            retryCallback.invoke()
        }
        return LoadStateViewHolder(layout)
    }

    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val pbLoading: ProgressBar = view.findViewById(R.id.pb_loading)
        private val tvError: TextView = view.findViewById(R.id.tv_error)

        fun bind(state: LoadState) {
            when (state) {
                is LoadState.Loading -> {
                    pbLoading.visibility = View.VISIBLE
                    tvError.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    pbLoading.visibility = View.INVISIBLE
                    tvError.visibility = View.VISIBLE
                    tvError.text = state.error.localizedMessage
                }
            }
        }
    }
}