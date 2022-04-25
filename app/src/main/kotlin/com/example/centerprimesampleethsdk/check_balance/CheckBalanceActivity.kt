package com.example.centerprimesampleethsdk.check_balance

import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivityCheckBalanceBinding
import java.math.BigDecimal

class CheckBalanceActivity :
    BaseActivity<BigDecimal, CheckBalanceViewModel, ActivityCheckBalanceBinding>() {

    override val viewModel by viewModels<CheckBalanceViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivityCheckBalanceBinding =
        ActivityCheckBalanceBinding::inflate

    override fun onResult(result: BigDecimal) {
        if (result.toInt() != Integer.MIN_VALUE) {
            with(binding.balanceTxt) {
                text = "Eth balance: $result"
                visibility = View.VISIBLE
            }
        }
    }

    override fun initUI() {
        binding.checkBtn.setOnClickListener {
            var address = binding.address.text.toString()

            if (!address.startsWith("0x")) {
                address = "0x$address"
            }

            if (address.isNotEmpty()) {
                viewModel.getBalanceInEth(address, this)
            } else {
                showMessage("Please provide wallet address")
            }
        }
    }
}
