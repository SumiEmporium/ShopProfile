package com.sumi.shopmanager.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.sumi.shopmanager.model.order.ShopOrdersResponseItem

@BindingAdapter("android:itemHistory")
fun itemHistory(card: MaterialCardView, item: ShopOrdersResponseItem?) {
    if (item != null) {
        if (item.isVisible == true) {
            card.visibility = View.VISIBLE
        } else {
            card.visibility = View.GONE
        }
    }
}