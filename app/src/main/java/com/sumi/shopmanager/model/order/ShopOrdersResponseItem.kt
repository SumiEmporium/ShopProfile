package com.sumi.shopmanager.model.order

import com.sumi.shopmanager.util.Formatter

data class ShopOrdersResponseItem(
    val address: Any,
    val bookingID: Int,
    val cd: String,
    val clientID: Int,
    val code: String,
    val compnayID: Int,
    val deliveryDate: String,
    val dueAmount: Int,
    val paidAmount: Int,
    val products: Any,
    val rOuteFK: Int,
    val remarks: Any,
    val shopFK: Int,
    val shopName: Any,
    val statusID: Int?,
    val totalDiscount: Int,
    val totalItem: Int,
    val totalValue: Double,
    val typeID: Int,
    val userID: Int,
    val voucherID: Int,
    var isVisible: Boolean? = true
) {

    var orderID: String
        get() {
            return "Order ID # ${code}"
        }
        set(value) {
            orderID = value
        }

    var formattedDeliveryDate: String
        get() {
            return "${Formatter.getFormattedDeliveryDate(cd)} "

        }
        set(value) {
            formattedDeliveryDate = value
        }

    var totalItemOrder: String
        get() {
            return "Total Item : ${totalItem}"
        }
        set(value) {
            orderID = value
        }
}