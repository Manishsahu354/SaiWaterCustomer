package com.tejeet.saiwatercustomer.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tejeet.saiwatercustomer.Data.Model.MyOrder
import com.tejeet.saiwatercustomer.Viewmodel.MainViewModel
import com.tejeet.saiwatercustomer.databinding.FragmentOrderBinding
import com.tejeet.saiwatersuppliers.Constant.ResultData
import com.tejeet.saiwatersuppliers.Ui.Adapters.OrderItemClickListener
import com.tejeet.saiwatersuppliers.Ui.Adapters.OrdersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment(),OrderItemClickListener {

    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    lateinit var ordersAdapter: OrdersAdapter
    var orderList:MutableList<MyOrder> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)

        setUpRecyclerview()

        mainViewModel.getAllOrders("1","tejeetm@gmail.com")
            .observe(viewLifecycleOwner, Observer {response->

                when(response){
                    is ResultData.Loading -> {

                        binding.recyclerviewOrder.visibility = View.INVISIBLE
                        binding.lottieLoading.visibility = View.VISIBLE

                    }
                    is ResultData.Success -> {
                        binding.recyclerviewOrder.visibility = View.VISIBLE
                        binding.lottieLoading.visibility = View.INVISIBLE
                        response.data?.let {
                            ordersAdapter.updateData(it)
                        }
                    }
                    is ResultData.Exception ->{

                    }
                }
            })

        return binding.root
    }

    private fun setUpRecyclerview() {

        ordersAdapter = OrdersAdapter(orderList,this)
        binding.recyclerviewOrder.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewOrder.adapter = ordersAdapter

    }

    override fun onOrderItemClicked(customer: MyOrder) {

    }


}