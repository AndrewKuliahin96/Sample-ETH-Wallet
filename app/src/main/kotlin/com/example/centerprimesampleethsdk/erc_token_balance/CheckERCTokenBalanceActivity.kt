package com.example.centerprimesampleethsdk.erc_token_balance

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivityErc20TokenBalanceBinding
import java.math.BigDecimal

class CheckERCTokenBalanceActivity :
    BaseActivity<BigDecimal, CheckERCTokenBalanceViewModel, ActivityErc20TokenBalanceBinding>() {

    override val viewModel by viewModels<CheckERCTokenBalanceViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivityErc20TokenBalanceBinding =
        ActivityErc20TokenBalanceBinding::inflate

    override fun onResult(result: BigDecimal) {
        if (result.toInt() != Integer.MIN_VALUE) {
            binding.balanceTxt.text = "Token Balance :$result"
        }
    }

    override fun initUI() {
        binding.checkBtn.setOnClickListener {
            val address = binding.address.text.toString()
            val password = binding.walletPassword.text.toString()
            val contractAddress = binding.contractAddress.text.toString()

            if (address.isNotEmpty() && password.isNotEmpty() && contractAddress.isNotEmpty()) {
                val walletAddress = address.trim { it <= ' ' }
                val walletPassword = password.trim { it <= ' ' }
                val erc20TokenContractAddress = contractAddress.trim { it <= ' ' }

                viewModel.getTokenBalance(
                    walletAddress,
                    walletPassword,
                    erc20TokenContractAddress,
                    this
                )
            } else {
                showMessage("Fill fields!")
            }
        }
    }
}
