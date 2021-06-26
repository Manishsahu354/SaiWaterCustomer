package com.tejeet.saiwatercustomer.Data.Model


import com.google.gson.annotations.SerializedName

data class MyOrder(
    @SerializedName("dispatchtime")
    val dispatchtime: String,
    @SerializedName("filledby")
    val filledby: String,
    @SerializedName("filltime")
    val filltime: String,
    @SerializedName("oid")
    val oid: String,
    @SerializedName("orderStatus")
    val orderStatus: String,
    @SerializedName("orderdate")
    val orderdate: String,
    @SerializedName("tankerquantity")
    val tankerquantity: String
)