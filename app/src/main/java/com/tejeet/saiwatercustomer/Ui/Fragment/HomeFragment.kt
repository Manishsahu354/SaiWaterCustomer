package com.tejeet.saiwatercustomer.Ui.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tejeet.saiwatercustomer.Ui.Activity.ManualOrderActivity
import com.tejeet.saiwatercustomer.Viewmodel.MainViewModel
import com.tejeet.saiwatercustomer.databinding.FragmentHomeBinding
import com.tejeet.saiwatersuppliers.Constant.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    internal val UPI_PAYMENT = 0


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        observeData()

        binding.btnPaymentBill.setOnClickListener {
            payUsingUpi("2000","tejeet@ybl","tejeet","Water Payment")
        }

        binding.pullToRefresh.setOnRefreshListener {
            observeData()
            binding.pullToRefresh.isRefreshing = false;
        }

        binding.btnManualOrder.setOnClickListener {
            startActivity(Intent(requireContext(),ManualOrderActivity::class.java))
        }

        return binding.root
    }

    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
            mainViewModel.getDashboard("1","tejeetm@gmail.com")
                .observe(viewLifecycleOwner, Observer {response->

                    when(response){
                        is ResultData.Loading -> {

                            binding.lottieLoading.visibility = View.VISIBLE
                            binding.tankView.visibility = View.INVISIBLE
                            binding.tvWaterPercentage.visibility = View.INVISIBLE

                        }
                        is ResultData.Success -> {
                            response.data?.body()?.let {
                                binding.lottieLoading.visibility = View.INVISIBLE
                                binding.tankView.visibility = View.VISIBLE
                                binding.tvWaterPercentage.visibility = View.VISIBLE



                                val value = map(it.waterlevel.toInt(),53,18,0,100)

                                val watervalue = it.waterlevel.toInt()
                                val actualWaterLevel = ((60 -watervalue)*100)/60

                                binding.tankView.setPercent(value)
                                binding.tvWaterPercentage.text = value.toString()

                            }
                        }
                        is ResultData.Exception ->{

                        }
                    }

                })
        }
    }
    fun map(x:Int, in_min:Int,  in_max:Int,  out_min:Int,  out_max:Int):Int
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    fun payUsingUpi(amount: String, upiId: String, name: String, note: String) {

        val uri = Uri.parse("upi://pay").buildUpon()
            .appendQueryParameter("pa", upiId)
            .appendQueryParameter("pn", name)
            .appendQueryParameter("tn", note)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            .build()


        val upiPayIntent = Intent(Intent.ACTION_VIEW)
        upiPayIntent.data = uri

        // will always show a dialog to user to choose an app
        val chooser = Intent.createChooser(upiPayIntent, "Pay with")
        startActivityForResult(chooser, UPI_PAYMENT)


        // check if intent resolves
//        if (null != chooser.resolveActivity(packageManager)) {
//            startActivityForResult(chooser, UPI_PAYMENT)
//        } else {
//            Toast.makeText(requireContext(), "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show()
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}