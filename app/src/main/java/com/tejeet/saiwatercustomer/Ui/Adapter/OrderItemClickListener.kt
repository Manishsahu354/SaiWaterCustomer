package com.tejeet.saiwatersuppliers.Ui.Adapters

import com.tejeet.saiwatercustomer.Data.Model.MyOrder

interface OrderItemClickListener {
    fun onOrderItemClicked(customer:MyOrder)
}