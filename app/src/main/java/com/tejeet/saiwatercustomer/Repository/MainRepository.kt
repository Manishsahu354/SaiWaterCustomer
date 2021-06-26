package com.tejeet.saiwatersuppliers.Repository

import com.tejeet.saiwatercustomer.Data.Model.GetAllOrdersDTO
import com.tejeet.saiwatercustomer.Data.Model.GetDashboardDTO
import com.tejeet.saiwatercustomer.Data.Model.MyOrder
import com.tejeet.saiwatercustomer.Data.Model.OrderTankerResponseDTO
import com.tejeet.saiwatercustomer.Network.ApiService
import com.tejeet.saiwatersuppliers.Constant.ConstantsData.API_KEY
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(
   private val apiService: ApiService
){

   suspend fun orderTanker(userId:String,tankerQuantity:String): Response<OrderTankerResponseDTO>{
      return apiService.orderTanker("Ok",API_KEY,userId,tankerQuantity,"Nothing")
   }

   suspend fun getDashboard(userId:String,userEmail:String,): Response<GetDashboardDTO>{
      return apiService.getDashboard("Ok",API_KEY,userId,userEmail)
   }

   suspend fun getAllOrder(userId:String,userEmail:String,): MutableList<MyOrder>{
      return apiService.getAllOrder("Ok",API_KEY,userId,userEmail).body()?.myOrders as MutableList<MyOrder>
   }


}