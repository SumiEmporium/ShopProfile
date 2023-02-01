package com.sumi.shopmanager.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    fun getFormattedDeliveryDate(deliveryDate: String?): String? {
        try {
            var inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
            val d: Date = inputFormat.parse(deliveryDate)
            val outputFormat = SimpleDateFormat("MMMM dd, yyyy hh:mm aa", Locale.ENGLISH)
            return outputFormat.format(d)
        } catch (e: ParseException) {
        }
        return ""
    }
}