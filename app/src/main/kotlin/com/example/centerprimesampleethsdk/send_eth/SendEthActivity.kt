package com.example.centerprimesampleethsdk.send_eth

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivitySendEthBinding
import java.math.BigDecimal
import java.math.BigInteger

class SendEthActivity : BaseActivity<String, SendEthViewModel, ActivitySendEthBinding>() {

    override val viewModel by viewModels<SendEthViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivitySendEthBinding =
        ActivitySendEthBinding::inflate

    override fun onResult(result: String) {
        if (result.isNotEmpty()) {
            showMessage("Transaction Hash : $result")
        }
    }

    override fun initUI() {
        binding.sendEth.setOnClickListener {
            val address = binding.address.text.toString().trim { it <= ' ' }
            val ethAmount = binding.ethAmount.text.toString().trim { it <= ' ' }
            val gasLimit = binding.gasLimit.text.toString().trim { it <= ' ' }
            val receiverAddress = binding.receiverAddress.text.toString().trim { it <= ' ' }
            val password = binding.password.text.toString().trim { it <= ' ' }

            if (address.isNotEmpty() && ethAmount.isNotEmpty() && gasLimit.isNotEmpty() && receiverAddress.isNotEmpty() && password.isNotEmpty()) {
                viewModel.sendEther(
                    address,
                    password,
                    BigInteger("30000000000"), // TODO Fix
                    BigInteger(gasLimit),
                    BigDecimal(ethAmount),
                    receiverAddress,
                    this
                )
            }
        }
    }
}
