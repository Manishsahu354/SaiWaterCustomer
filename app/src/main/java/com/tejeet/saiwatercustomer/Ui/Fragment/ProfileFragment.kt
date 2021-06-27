package com.tejeet.saiwatercustomer.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tejeet.saiwatercustomer.R
import com.tejeet.saiwatercustomer.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    var toggleState:Boolean= true

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnToggle.setOnClickListener {
            if (toggleState){
                binding.idOrdersLayout.visibility = View.GONE
                toggleState = false
            }else{
                binding.idOrdersLayout.visibility = View.VISIBLE
                toggleState = true
            }
        }
        return binding.root
    }

}