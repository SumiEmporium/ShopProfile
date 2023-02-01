package com.sumi.shopmanager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumi.shopmanager.api.Resource
import com.sumi.shopmanager.model.order.OrderPostDataModel
import com.sumi.shopmanager.model.order.ShopOrdersResponse
import com.sumi.shopmanager.model.shopinfo.ShopInfoResponse
import com.sumi.shopmanager.model.shopinfo.ShopPostDataModel
import com.sumi.shopmanager.repository.RestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val repository: RestRepository) : ViewModel() {
    var shopInfoResponse: MutableLiveData<Resource<ShopInfoResponse>> = MutableLiveData()
    var ordersResponse: MutableLiveData<Resource<ShopOrdersResponse>> = MutableLiveData()

    fun getShopInfo(userid: Int, companyId: Int) {
        viewModelScope.launch {
            shopInfoResponse.postValue(Resource.loading(data = null))

            try {
                shopInfoResponse.postValue(
                    Resource.success(
                        data = repository.getShopInfo(
                            ShopPostDataModel(userid, companyId, 8)
                        )
                    )
                )
            } catch (e: Exception) {
                shopInfoResponse.postValue(
                    Resource.error(
                        data = null,
                        message = e.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }

    fun getOrdersData(userid: Int, companyId: Int, shopFk: Int, statusId: Int) {
        viewModelScope.launch {
            ordersResponse.postValue(Resource.loading(data = null))

            try {
                ordersResponse.postValue(
                    Resource.success(
                        data = repository.getShopOrders(
                            OrderPostDataModel(userid, companyId, shopFk, statusId)
                        )
                    )
                )
            } catch (e: Exception) {
                ordersResponse.postValue(
                    Resource.error(
                        data = null,
                        message = e.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }
}