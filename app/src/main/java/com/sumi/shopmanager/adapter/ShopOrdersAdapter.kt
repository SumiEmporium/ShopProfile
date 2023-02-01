package com.sumi.shopmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sumi.shopmanager.databinding.ListItemShopOrdersBinding
import com.sumi.shopmanager.model.order.ShopOrdersResponseItem


class ShopOrdersAdapter() :
    ListAdapter<ShopOrdersResponseItem, ShopOrdersAdapter.ViewHolder>(ShopDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemShopOrdersBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ListItemShopOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShopOrdersResponseItem) {
            binding.orderitem = item

            binding.executePendingBindings()
        }

    }

}

class ShopDiffCallBack : DiffUtil.ItemCallback<ShopOrdersResponseItem>() {
    override fun areItemsTheSame(
        oldItem: ShopOrdersResponseItem,
        newItem: ShopOrdersResponseItem
    ): Boolean {
        return oldItem.bookingID == newItem.bookingID
    }

    override fun areContentsTheSame(
        oldItem: ShopOrdersResponseItem,
        newItem: ShopOrdersResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}
