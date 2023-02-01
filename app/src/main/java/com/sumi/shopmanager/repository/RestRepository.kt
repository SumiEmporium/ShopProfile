package com.sumi.shopmanager.repository

import com.google.gson.Gson
import com.sumi.shopmanager.api.ApiService
import com.sumi.shopmanager.model.order.OrderPostDataModel
import com.sumi.shopmanager.model.order.ShopOrdersResponse
import com.sumi.shopmanager.model.shopinfo.ShopInfoResponse
import com.sumi.shopmanager.model.shopinfo.ShopPostDataModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class RestRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getShopInfo(payload: ShopPostDataModel): ShopInfoResponse {
        val gson = Gson()
        val params = gson.toJson(payload)

        val JSON = "application/json; charset=utf-8".toMediaType()
        val body = JSONObject(params).toString().toRequestBody(JSON)

        return apiService.getShopInfo(body)
    }

    suspend fun getShopOrders(payload: OrderPostDataModel): ShopOrdersResponse {
        val gson = Gson()
        val params = gson.toJson(payload)

        val JSON = "application/json; charset=utf-8".toMediaType()
        val body = JSONObject(params).toString().toRequestBody(JSON)

        return apiService.getOrders(body)
    }
}