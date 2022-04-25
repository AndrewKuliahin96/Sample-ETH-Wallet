package com.example.centerprimesampleethsdk.send_erc_token

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivitySendErc20TokenBinding
import java.math.BigDecimal
import java.math.BigInteger

class SendERCTokenActivity :
    BaseActivity<String, SendERCTokenViewModel, ActivitySendErc20TokenBinding>() {

    override val viewModel by viewModels<SendERCTokenViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivitySendErc20TokenBinding =
        ActivitySendErc20TokenBinding::inflate

    override fun onResult(result: String) {
        if (result.isNotEmpty()) {
            showMessage("Transaction Hash : $result")
        }
    }

    override fun initUI() {
        binding.sendERCToken.setOnClickListener {
            val address = binding.address.text.toString()
            val ethAmount = binding.ethAmount.text.toString().trim { it <= ' ' }
            val gasLimit = binding.gasLimit.text.toString()
            val receiverAddress = binding.receiverAddress.text.toString().trim { it <= ' ' }
            val password = binding.password.text.toString()
            val contractAddress = binding.contractAddress.text.toString().trim { it <= ' ' }

            if (address.trim { it <= ' ' }
                    .isNotEmpty() && ethAmount.isNotEmpty() && gasLimit.trim { it <= ' ' }
                    .isNotEmpty() && receiverAddress.isNotEmpty() && password.trim { it <= ' ' }
                    .isNotEmpty() && contractAddress.isNotEmpty()) {

                viewModel.sendToken(
                    address,
                    password,
                    BigInteger("30000000000"), // TODO Change gas price
                    BigInteger(gasLimit),
                    BigDecimal(ethAmount),
                    receiverAddress,
                    contractAddress,
                    this
                )
            }
        }
    }
}
