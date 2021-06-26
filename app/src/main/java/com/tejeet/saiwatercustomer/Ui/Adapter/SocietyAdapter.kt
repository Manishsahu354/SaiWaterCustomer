package com.tejeet.saiwatersuppliers.Ui.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.saiwatercustomer.Data.Model.MyOrder
import com.tejeet.saiwatercustomer.R

class OrdersAdapter(private var dataList:MutableList<MyOrder>, private val itemClickListener: OrderItemClickListener)
    :RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_order,parent,false)
        return   OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        holder.apply {

                tvOrderTime.text = dataList[position].orderdate
                tvDeliveryBoy.text = dataList[position].filledby
                tvDeliveryTime.text = dataList[position].dispatchtime

            val status = dataList[position].orderStatus.toInt()
            if (status==0){
                idSalesUserStatus.text = "Pending"
                idSalesUserStatus.setTextColor(Color.parseColor("#FF0000"));
            }else if(status==1){
                idSalesUserStatus.text = "On Going"
                idSalesUserStatus.setTextColor(Color.parseColor("#F17F05"));
            }else {
                idSalesUserStatus.text = "Fulfilled"
                idSalesUserStatus.setTextColor(Color.parseColor("#4BEC00"));
            }





            cvOrderItem.setOnClickListener {
                itemClickListener.onOrderItemClicked(dataList[position])

            }
        }
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    fun updateData( newData:MutableList<MyOrder>){
        dataList = newData
        notifyDataSetChanged()
    }


    class OrderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

         val tvDeliveryBoy = itemView.findViewById<TextView>(R.id.tvDeliveryBoy)
         val tvOrderTime = itemView.findViewById<TextView>(R.id.tvOrderTime)
         val tvDeliveryTime = itemView.findViewById<TextView>(R.id.tvDeliveryTime)
         val idSalesUserStatus = itemView.findViewById<TextView>(R.id.idSalesUserStatus)
         val cvOrderItem = itemView.findViewById<CardView>(R.id.cvOrderItemLayout)

    }




}