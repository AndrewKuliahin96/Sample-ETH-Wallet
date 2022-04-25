package com.example.centerprimesampleethsdk.create_wallet

import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivityCreateWalletBinding

class CreateWalletActivity : BaseActivity<String, CreateWalletViewModel, ActivityCreateWalletBinding>() {

    override val viewModel by viewModels<CreateWalletViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivityCreateWalletBinding =
        ActivityCreateWalletBinding::inflate

    override fun onResult(result: String) {
        if (result.isNotEmpty()) {
            with(binding.address) {
                text = "0x$result"
                visibility = View.VISIBLE
            }
        }
    }

    override fun initUI() {
        binding.createWallet.setOnClickListener {
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            if (password.isNotEmpty() && confirmPassword.isNotEmpty() &&
                password == confirmPassword && password.trim { it <= ' ' }.length >= 6 &&
                confirmPassword.trim { it <= ' ' }.length >= 6
            ) {
                viewModel.createWallet(binding.password.text.toString(), this)
            } else {
                showMessage("Please insert password correctly.")
            }
        }

        binding.copy.setOnClickListener { copyToClipboard(binding.address.text.toString()) }
    }
}
