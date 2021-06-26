package com.tejeet.saiwatercustomer.Data.Model


import com.google.gson.annotations.SerializedName

data class GetDashboardDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("totalamount")
    val totalamount: String,
    @SerializedName("waterlevel")
    val waterlevel: String
)