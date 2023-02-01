package com.sumi.shopmanager.model.order

data class OrderPostDataModel(
    val UserID: Int,
    val CompanyID: Int,
    val ShopFK: Int,
    val StatusID: Int
)
