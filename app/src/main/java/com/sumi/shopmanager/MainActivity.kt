package com.sumi.shopmanager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sumi.shopmanager.adapter.ShopOrdersAdapter
import com.sumi.shopmanager.api.Status
import com.sumi.shopmanager.databinding.ActivityMainBinding
import com.sumi.shopmanager.model.order.ShopOrdersResponseItem
import com.sumi.shopmanager.viewmodel.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ShopViewModel
    private lateinit var adapter: ShopOrdersAdapter
    var orderList: MutableList<ShopOrdersResponseItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]
        adapter = ShopOrdersAdapter()

        viewModel.getShopInfo(120, 29)

        subscribeObserver()

        binding.cardConfirmed.setOnClickListener {
            viewModel.getOrdersData(120, 29, 8, 12)
        }
        binding.cardPartialDelivered.setOnClickListener {
            viewModel.getOrdersData(120, 29, 8, 15)
        }
        binding.cardDelivered.setOnClickListener {
            viewModel.getOrdersData(120, 29, 8, 9)
        }

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().toLowerCase(Locale.getDefault())
            filter(query)
        }
    }

    private fun subscribeObserver() {
        viewModel.shopInfoResponse.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    Log.e("shopInfoResponse", "loading")
                }

                Status.SUCCESS -> {
                    Log.e("shopInfoResponse", "SUCCESS")
                    binding.shop = it.data
                    viewModel.getOrdersData(120, 29, 8, 0)
                }

                Status.ERROR -> {
                    Log.e("shopInfoResponse", "ERROR${it.message}")
                }
            }
        }

        viewModel.ordersResponse.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    Log.e("ordersResponse", "loading")
                }

                Status.SUCCESS -> {
                    Log.e("ordersResponse", "SUCCESS")
                    orderList = it.data!!
                   // adapter.submitList(orderList)
                    binding.rvOrders.adapter = adapter
                }

                Status.ERROR -> {
                    Log.e("ordersResponse", "ERROR${it.message}")

                }
            }
        }
    }
    private fun filter(text: String) {

        val filteredList: MutableList<ShopOrdersResponseItem> = mutableListOf()
        for (item in orderList) {
            if (item.code.lowercase().contains(text.lowercase())) {

                filteredList.add(item)
            }

            adapter.submitList(filteredList)
        }

    }
}