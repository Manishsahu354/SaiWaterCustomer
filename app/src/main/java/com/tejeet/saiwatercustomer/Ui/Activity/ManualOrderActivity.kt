package com.tejeet.saiwatercustomer.Ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.tejeet.saiwatercustomer.R
import com.tejeet.saiwatercustomer.Viewmodel.MainViewModel
import com.tejeet.saiwatercustomer.databinding.ActivityManualOrderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ManualOrderActivity : AppCompatActivity() {

    lateinit var binding:ActivityManualOrderBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAddOrder.setOnClickListener {
            binding.apply {


                if (binding.tankerQuntity.text.isEmpty()) {
                    binding.tankerQuntity.error = "please fill Quantity"

                } else {

                    CoroutineScope(Dispatchers.Main).launch {
                        val response = mainViewModel.orderTanker(
                            "1", tankerQuntity.text.toString()
                        )

                        if (response.body()?.requeststatus == 1){
                            tvOrderSuccessful.visibility = View.VISIBLE
                            llBtnGoHome.visibility = View.VISIBLE
                            llTankerQuantity.visibility = View.GONE
                            llAddOrderButton.visibility = View.GONE


                        }else{
                            llTankerQuantity.visibility = View.GONE
                            llAddOrderButton.visibility = View.GONE
                            tvOrderSuccessful.visibility = View.VISIBLE
                            tvOrderSuccessful.text = response.body()?.message
                        }

                    }
                }
            }
        }

        binding.btnGoHome.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}