package com.raihan.testportal.presentation.thirdscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.raihan.testportal.data.model.User
import com.raihan.testportal.databinding.ItemListBinding

class UserAdapter(private val listener: (User) -> Unit) :
    PagingDataAdapter<User, UserAdapter.ItemUserViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemUserViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemUserViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: ItemUserViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        item?.let { holder.bindView(it) }
    }

    class ItemUserViewHolder(
        private val binding: ItemListBinding,
        val itemClick: (User) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: User) {
            with(item) {
                binding.ivProfileImages.load(item.avatar) {
                    crossfade(true)
                }
                binding.tvUserName.text = "${item.firstName} ${item.lastName}"
                binding.tvUserEmail.text = item.email
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldItem: User,
                    newItem: User,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: User,
                    newItem: User,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            }
    }
}
