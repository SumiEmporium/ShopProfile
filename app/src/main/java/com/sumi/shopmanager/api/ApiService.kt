package com.sumi.shopmanager.api

import com.sumi.shopmanager.model.order.ShopOrdersResponse
import com.sumi.shopmanager.model.shopinfo.ShopInfoResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("Shop")
    suspend fun getShopInfo(@Body param: RequestBody): ShopInfoResponse

    @POST("Orders")
    suspend fun getOrders(@Body param: RequestBody): ShopOrdersResponse
}