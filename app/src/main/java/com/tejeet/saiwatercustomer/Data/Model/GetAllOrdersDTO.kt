package com.tejeet.saiwatercustomer.Data.Model


import com.google.gson.annotations.SerializedName

data class GetAllOrdersDTO(
    @SerializedName("MyOrders")
    val myOrders: List<MyOrder>,
    @SerializedName("nooforders")
    val nooforders: Int,
    @SerializedName("requeststatus")
    val requeststatus: Int
)