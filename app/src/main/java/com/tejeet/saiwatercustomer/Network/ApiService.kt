package com.tejeet.saiwatercustomer.Network

import com.tejeet.saiwatercustomer.Data.Model.GetAllOrdersDTO
import com.tejeet.saiwatercustomer.Data.Model.GetDashboardDTO
import com.tejeet.saiwatercustomer.Data.Model.OrderTankerResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @GET("api/app.php?")
    suspend fun orderTanker(
        @Query("orderTanker") orderTanker : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userID") userID : String,
        @Query("tankerQuantity") tankerQuantity: String,
        @Query("orderNote") orderNote : String
    ): Response<OrderTankerResponseDTO>

    @GET("api/app.php?")
    suspend fun getDashboard(
        @Query("getDashboard") getDashboard : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userid") userid : String,
        @Query("useremail") useremail: String
    ): Response<GetDashboardDTO>

    @GET("api/app.php?")
    suspend fun getAllOrder(
        @Query("getAllOrders") getAllOrders : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userid") userid : String,
        @Query("useremail") useremail: String
    ): Response<GetAllOrdersDTO>


}